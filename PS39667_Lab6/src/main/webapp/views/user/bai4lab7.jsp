<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Employee Management - REST Consumer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        input, button {
            margin: 10px 0;
            padding: 5px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .error {
            color: red;
            display: none;
        }
    </style>
</head>
<body>
    <div>
        <input id="id" placeholder="Id?" >
        <div id="idError" class="error">ID cannot be empty</div>
        <br>
        <input id="name" placeholder="Name?" required>
        <div id="nameError" class="error">Name cannot be empty</div>
        <br>
        <input type="radio" id="male" name="gender" checked> Male
        <input type="radio" id="female" name="gender"> Female<br>
        <input id="salary" type="number" placeholder="Salary?" min="0">
        <div id="salaryError" class="error">Salary must be a positive number</div>
        <hr>
        <button onclick="ctrl.create()">Create</button>
        <button onclick="ctrl.update()">Update</button>
        <button onclick="ctrl.delete()">Delete</button>
        <button onclick="ctrl.reset()">Reset</button>
    </div>
    <hr>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Salary</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="list"></tbody>
    </table>

    <script>
    var ctrl = {
    

    	    setForm: function (employee) {
    	        document.getElementById("id").value = employee.id || "";
    	        document.getElementById("name").value = employee.name || "";
    	        document.getElementById("salary").value = employee.salary || 0;
    	        if (employee.gender) {
    	            document.getElementById("male").checked = true;
    	        } else {
    	            document.getElementById("female").checked = true;
    	        }
    	    },

    	    getForm: function () {
    	        return {
    	            id: document.getElementById("id").value.trim(),
    	            name: document.getElementById("name").value.trim(),
    	            gender: document.getElementById("male").checked,
    	            salary: parseFloat(document.getElementById("salary").value) || 0
    	        };
    	    },

    	    fillToTable: function (employees) {
    	        var rows = "";
    	        employees.forEach(function (employee) {
    	            var row = "<tr>" +
    	                "<td>" + employee.id + "</td>" +
    	                "<td>" + employee.name + "</td>" +
    	                "<td>" + (employee.gender ? "Male" : "Female") + "</td>" +
    	                "<td>" + (employee.salary ? employee.salary.toLocaleString() : 0) + "</td>" +
    	                "<td><a href='javascript:ctrl.edit(\"" + employee.id + "\")'>Edit</a></td>" +
    	                "</tr>";
    	            rows += row;
    	        });
    	        document.getElementById("list").innerHTML = rows;
    	    },

    	    loadAll: function () {
    	        var url = "http://localhost:8080/PS39667_Lab6/employees";
    	        fetch(url, { method: "GET" })
    	            .then(function (resp) {
    	                if (!resp.ok) {
    	                    throw new Error("Failed to fetch employees");
    	                }
    	                return resp.json();
    	            })
    	            .then(function (employees) {
    	                ctrl.fillToTable(employees);
    	            })
    	            .catch(function (error) {
    	                console.error("Error:", error);
    	                alert("Failed to load employees");
    	            });
    	    },

    	    create: function () {
    	        if (!this.validate()) return;

    	        var data = this.getForm();
    	        var url = "http://localhost:8080/PS39667_Lab6/employees";
    	        fetch(url, {
    	            method: "POST",
    	            headers: { "Content-Type": "application/json" },
    	            body: JSON.stringify(data)
    	        })
    	            .then(function (resp) {
    	                if (!resp.ok) {
    	                    throw new Error("Failed to create employee");
    	                }
    	                return resp.json();
    	            })
    	            .then(function () {
    	                ctrl.loadAll();
    	                ctrl.reset();
    	            })
    	            .catch(function (error) {
    	                console.error("Error:", error);
    	                alert("Failed to create employee");
    	            });
    	    },

    	    update: function () {
    	        if (!this.validate()) return;

    	        var data = this.getForm();
    	        if (!data.id) {
    	            alert("Please select an employee to update");
    	            return;
    	        }

    	        var url = "http://localhost:8080/PS39667_Lab6/employees/" + data.id;
    	        fetch(url, {
    	            method: "PUT",
    	            headers: { "Content-Type": "application/json" },
    	            body: JSON.stringify(data)
    	        })
    	            .then(function (resp) {
    	                if (!resp.ok) {
    	                    throw new Error("Failed to update employee");
    	                }
    	                ctrl.loadAll();
    	            })
    	            .catch(function (error) {
    	                console.error("Error:", error);
    	                alert("Failed to update employee");
    	            });
    	    },

    	    delete: function () {
    	        var id = document.getElementById("id").value;
    	        if (!id) {
    	            alert("Please select an employee to delete");
    	            return;
    	        }

    	        var url = "http://localhost:8080/PS39667_Lab6/employees/" + id;
    	        fetch(url, { method: "DELETE" })
    	            .then(function (resp) {
    	                if (!resp.ok) {
    	                    throw new Error("Failed to delete employee");
    	                }
    	                ctrl.loadAll();
    	                ctrl.reset();
    	            })
    	            .catch(function (error) {
    	                console.error("Error:", error);
    	                alert("Failed to delete employee");
    	            });
    	    },

    	    reset: function () {
    	        var employee = { id: "", name: "", salary: 0, gender: true };
    	        this.setForm(employee);
    	        document.getElementById("nameError").style.display = "none";
    	        document.getElementById("salaryError").style.display = "none";
    	    },

    	    edit: function (id) {
    	        var url = "http://localhost:8080/PS39667_Lab6/employees/" + id;
    	        fetch(url, { method: "GET" })
    	            .then(function (resp) {
    	                if (!resp.ok) {
    	                    throw new Error("Failed to fetch employee");
    	                }
    	                return resp.json();
    	            })
    	            .then(function (employee) {
    	                ctrl.setForm(employee);
    	            })
    	            .catch(function (error) {
    	                console.error("Error:", error);
    	                alert("Failed to fetch employee");
    	            });
    	    }
    	};

    	// Khởi chạy khi trang load xong
    	window.onload = function () {
    	    ctrl.loadAll();
    	};


    </script>
</body>
</html>
