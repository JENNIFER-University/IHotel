<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">

    <h1 class="title">Create Account</h1>


    <div class="room-features">

        <div class="row">

            <form action="doSignUp" method="post">

                <div class="panel panel-info">

                    <div class="panel-heading">
                        Signup Form
                    </div>

                    <div class="panel-body">

                        <div class="form-group">
                            <label>Full Name:</label>
                            <input type="text" class="form-control" id="realName" name="realName" required>
                        </div>


                        <div class="form-group">
                            <label>Username:</label>
                            <input type="text" class="form-control" id="username" name="userName" required>
                        </div>

                        <div class="form-group">
                            <label>Password:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                    </div>

                    <div class="panel-footer">
                        <button id="bookNow" type="submit" class="btn btn-default btn-lg">
                            <i class="fa fa-credit-card" aria-hidden="true"></i>
                            Login
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>

