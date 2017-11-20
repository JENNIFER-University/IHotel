<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">

    <h1 class="title">Login</h1>


    <div class="room-features">

        <div class="row">

            <form action="doLogin" method="get">

            <div class="panel panel-info">

                <div class="panel-heading">
                    Login Form
                </div>

                <div class="panel-body">

                    <div class="form-group">
                        <label>Username:</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>

                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                </div>

                <div class="panel-footer">
                    <button id="bookNow" type="submit" class="btn btn-default btn-lg">
                        <i class="fa fa-credit-card" aria-hidden="true"></i>
                        Login
                     </button>
                    <div class="pull-right">
                        <a href="signup">Do not have an account? Sign Up Now</a>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>

</div>

