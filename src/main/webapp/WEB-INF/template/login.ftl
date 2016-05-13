<!doctype html>
<html>
<head>
    <title>Login from</title>
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <#if error??&&error>
    <div class="alert alert-danger">
        There was a problem logging in. Please try again.
    </div>
    </#if>
    <form role="form" action="/login.action" method="post">
        <div class="form-group">
            <label for="username">Username:</label> <input type="text"
                                                           class="form-control" id="username" placeholder="tomLuo" name="account.username"/>
        </div>
        <div class="form-group">
            <label for="password">Password:</label> <input type="password"
                                                           class="form-control" id="password" placeholder="tomLuo" name="account.password" />
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
<script src="/static/js/front.js"></script>
</body>
</html>