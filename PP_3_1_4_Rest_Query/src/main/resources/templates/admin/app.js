async function findAll() {
    const res = await fetch('http://localhost:8080/api/users')
    const users = await res.json();

    users.forEach(user => allUsersHTML(user))
}

window.addEventListener('DOMContentLoaded', findAll);

function allUsersHTML({id, username, password, email}) {
    const usersList = document.getElementById('mainTable');

    usersList.insertAdjacentHTML('beforeend',  `
        <div className="container">
            <div id="user${id}">
                ${username}
                <!--<table className="table table-striped table-bordered table-hover caption-top">
                    <caption><p className="fw-bold">All users</p></caption>
                    <thead className="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody id="usersTable">
                        <tr>
                            <td>${id}</td>
                            <td>${username}</td>
                            <td>${password}</td>
                            <td>${email}</td>
                        </tr>
                    </tbody>
                </table>
                -->
            </div>
        </div>
    `)
}