    const loginForm = document.querySelector('form');
    
    loginForm.addEventListener('submit', async (e) => {
       
        e.preventDefault();
        
       
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        try {
            
            const response = await fetch('http://localhost:8080/api/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            const data = await response.json();
            console.log('Login response:', response.status, data);

            if (response.ok) {
                alert("Selamat datang, " + data.user.nama_lengkap + "!");

                localStorage.setItem('userName', data.user.nama_lengkap);
                localStorage.setItem('userRole', data.user.role);
                localStorage.setItem('userId', data.user.id);

                if (data.user.role === 'admin') {
                    window.location.href = "adminPanel.html";
                } else {
                    window.location.href = "katalog.html";
                }
            } else {
                const errorMessage = data.message || data.error || JSON.stringify(data);
                alert(errorMessage);
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Gagal terhubung ke server. Pastikan backend Spring Boot sudah nyala di localhost:8080!");
        }
    });
