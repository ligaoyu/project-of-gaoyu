<!DOCTYPE html>
<html lang="en">
<head>
	<#assign path="${request.getContextPath()}">
    <meta charset="utf-8">
    <title>Assets Tracking System</title>

    <link href="${path}/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${path}/css/simple-line-icons.css" rel="stylesheet" type="text/css">
    <link href="${path}/css/style.css" rel="stylesheet">

</head>
<body class="app flex-row align-items-center">
<form action="${path}/login/check/" method="post" >
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card-group mb-0">
                    <div class="card p-2">
                        <div class="card-block">
                            <h1>Login</h1>
                            <p class="text-muted">Sign In to your account</p>
                            <div class="input-group mb-1">
                                <span class="input-group-addon"><i class="icon-user"></i>
                                </span>
                                <input type="text" name="username" class="form-control" placeholder="Username">
                            </div>
                            <div class="input-group mb-2">
                                <span class="input-group-addon"><i class="icon-lock"></i>
                                </span>
                                <input type="password" name="password" class="form-control" placeholder="Password">
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <button type="button" onclick="submit();" class="btn btn-primary px-2">Login</button>
                                </div>
                                <div class="col-6 text-right">
                                    <button type="button" class="btn btn-link px-0">Forgot password?</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
</form>

    <!-- Bootstrap and necessary plugins -->
	<script type="text/JavaScript" src="${path}/js/jquery.min.js"></script>
	<script type="text/JavaScript" src="${path}/js/tether.min.js"></script>
	<script type="text/JavaScript" src="${path}/js/bootstrap.min.js"></script>

</body>

</html>