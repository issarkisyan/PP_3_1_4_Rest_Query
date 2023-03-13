userInfo();

function userInfo() {
    fetch("http://localhost:8080/user/userAuth")
        .then((res) => res.json())
        .then((user) => {
            console.log(user);
            document.getElementById("headerUserName").innerHTML = user.email;
            let roles =  user.roles.map(e => e.role) ;
            let data = `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${roles}</td>
                </tr>`;
            document.getElementById("userAuthTable").innerHTML = data;
        })
}

