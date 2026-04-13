 const registerForm = document.querySelector('form');
    
    registerForm.addEventListener('submit', async (e) => {
      
        e.preventDefault();
        
        
        const nama_lengkap = document.getElementById('nama_lengkap').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const agreement = document.getElementById('agreement').checked;


        if (!agreement) {
            alert("Anda harus menyetujui Syarat & Ketentuan!");
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/api/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ nama_lengkap, email, password })
            });

            const data = await response.json();
            console.log('Register response:', response.status, data);

            if (response.ok) {
                alert("Registrasi Berhasil! Silakan masuk.");
                window.location.href = "login.html";
            } else {
                const errorMessage = data.message || data.error || JSON.stringify(data);
                alert(errorMessage);
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Gagal terhubung ke server. Pastikan backend Spring Boot sudah nyala di localhost:8080!");
        }
    });