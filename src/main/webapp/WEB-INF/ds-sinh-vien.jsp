<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        .title-box {
            padding: 3rem 0 2rem 0;
            text-align: center;
            display: flex;
        }

        .btn-nueva {
            margin: 2px 0 14px 0;
        }
    </style>
</head>

<body>
    <div class="container">

        <div class="title-box">
            <a href="/" type="button" class="btn btn-primary btn-nueva">
                Home
            </a>
            <h1 class="flex-grow-1">QUẢN LÝ SINH VIÊN</h1>

        </div>
        <div class="header-box mb-3">
            <button type="button" class="btn btn-primary btn-nueva" data-bs-toggle="modal" data-bs-target="#modal-add">
                <b>+</b> Add
            </button>
            <div class="input-group">
                <div class="form-outline " style="height: 38px;">
                    <input type="search" id="form1" class="form-control" placeholder="Search" />
                    <label class="form-label" for="form1"></label>
                </div>
                <button type="button" class="btn btn-primary " style="height: 38px;" >
                    <i class="fas fa-search"></i>
                </button>
            </div>
        </div>


        <table class="table table-bordered grocery-crud-table table-hover">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Mã sinh viên</th>
                    <th>Tên sinh viên</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>
                            ${student.id}
                        </td>
                        <td>
                                ${student.code}
                        </td>
                        <td>
                                ${student.name}
                        </td>
                        <td>
                            <button type="button" class="btn btn-default btn-outline-dark" data-bs-toggle="modal"
                                    data-bs-target="#modal-edit">
                                Edit
                            </button>
                            <form action="/admin/table/delete/${table.id}" method="post">
                                <button type="submit" class="btn btn-default btn-outline-dark">
                                    Delete
                                </button>
                            </form>



                        </td>
                    </tr>
                </c:forEach>
                


            </tbody>
        </table>

    </div>
    <!-- Modal Edit -->
    <div class="modal fade" id="modal-edit" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
        aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Edit form</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="/admin/table/update" method="post">
                    <div class="modal-body">
                        <div class="row g-4">
                            <div class="col-12">
                                <input name="id" type="number" class="form-control" placeholder="Id" aria-label="Id">
                            </div>
                            <div class="col-12">
                                <input name="deskAmount" type="number" class="form-control" placeholder="Số ghế" aria-label="Mã sinh viên">
                            </div>
                            <div class="col-12">
                                <input name="deskAmount" type="number" class="form-control" placeholder="Số ghế" aria-label="Tên sinh viên">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Modal Add -->
    <div class="modal fade" id="modal-add" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
        aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Add form</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="/student" method="post">
                    <div class="modal-body">
                        <div class="row g-4">
                            <div class="col-12">

                                <input type="text" name="code" min="0" class="form-control" placeholder="Mã sinh viên" aria-label="Mã sinh viên">
                            </div>
                            <div class="col-12">

                                <input type="text" name="name" min="0" class="form-control" placeholder="Tên sinh viên" aria-label="Tên sinh viên">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</body>

</html>