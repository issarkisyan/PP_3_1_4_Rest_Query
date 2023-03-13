
var editFormData;
//For add
function getFormData() {
    return {
        username:document.getElementById("username").value,
        password:document.getElementById("password").value,
        email:document.getElementById("email").value,
        //role:document.getElementById("role").value
    }
}

function clearFormData() {
    document.getElementById("username").value = "";
    document.getElementById("password").value = "";
    document.getElementById("email").value = "";
}

function setFormData(username, password, email) {
    document.getElementById("username").value = username;
    document.getElementById("password").value = password;
    document.getElementById("email").value = email;
}
// For edit
function getEditFormData() {
    return {
        username:document.getElementById("usernameEdit").value,
        password:document.getElementById("passwordEdit").value,
        email:document.getElementById("emailEdit").value,
        //role:document.getElementById("role").value
    }
}

function clearEditFormData() {
    document.getElementById("usernameEdit").value = "";
    document.getElementById("passwordEdit").value = "";
    document.getElementById("emailEdit").value = "";
}

function setEditFormData(username, password, email) {
    document.getElementById("usernameEdit").value = username;
    document.getElementById("passwordEdit").value = password;
    document.getElementById("emailEdit").value = email;
}

/*
// set the message for form status
function setSuccessMessage(message) {
    document.getElementById("message").innerHTML = message;
}

 */
function editDataCall(id) {
    // call get user details by id API
    fetch("http://localhost:8080/api/users/"+id,{
        method:"GET"
    }).then((res)=>res.json()).then((response)=>{
        console.log("Edit info",response);
        editFormData =  response;
        setEditFormData(editFormData.username, editFormData.password, editFormData.email)
    })
}

// форма для добавления юзера
function submitForm() {
    addUser();
}
// форма для редактирования юзера
function editForm() {
    editData(editFormData.id);
}
// add user function
function addUser() {
    let payload  = getFormData();
    fetch("http://localhost:8080/api/users",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(payload)
    }).then((response)=>{
        //setSuccessMessage(response.message)
        // clear input email and name
        clearFormData();
        getData(); // reload table
    })
}

// edit data
function editData(id) {
    var formData = getEditFormData();
    // formData['id'] = editFormData._id; // get _id from selected user
    fetch("http://localhost:8080/api/users/"+id,{
        method:"PATCH",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(formData)
    }).then((response)=>{
        //setSuccessMessage(response.message)
        clearEditFormData(); // clear the form field
        getData() // reload the table
    })
}

// delete data
function deleteData(id) {
    fetch("http://localhost:8080/api/users/"+id, {
        method:"DELETE",
        headers: {
            "Content-Type":"application/json"
        }
    }).then((response)=>{
        //setSuccessMessage(response.message);
        getData();
    })
}
// get data in second menu
function getSecondData() {
    fetch("http://localhost:8080/user/userAuth",{
        method:"GET",
        headers: {
            "Content-Type":"application/json"
        }
    })
        .then((res) => res.json())
        .then((user) => {
            console.log(user);
            let roles =  user.roles.map(e => e.role) ;
            let data = `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${roles}</td>
                </tr>`;
            document.getElementById("headerUserName").innerHTML = user.email;
            document.getElementById("secondNavData").innerHTML = data;
        })
}



// get data method

function getData() {
    fetch("http://localhost:8080/api/users").then(
        (res)=>res.json()
    ).then((response)=>{
        var tmpData  = "";
        console.log(response);
        response.forEach((user)=>{
            tmpData+="<tr>"
            tmpData+="<td>"+user.id+"</td>";
            tmpData+="<td>"+user.username+"</td>";
            tmpData+="<td>"+user.password+"</td>";
            tmpData+="<td>"+user.email+"</td>";
            tmpData+="<td>"+user.roles.map(e => e.role)+"</td>";
            tmpData+="<td><button class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#editModel' onclick='editDataCall(`"+user.id+"`)'>Edit</button></td>";
            tmpData+="<td><button class='btn btn-danger'  onclick='deleteData(`"+user.id+"`)'>Delete</button></td>";

            tmpData+="</tr>";
        })
        document.getElementById("tbData").innerHTML = tmpData;
    })
}

getData();
getSecondData()