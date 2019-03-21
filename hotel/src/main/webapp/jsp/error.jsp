<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container" style="margin-top: 50px; margin-bottom: 50px;">

    <div class="jumbotron">
        <h1 class="display-3">OOOOPs</h1>
        <p class="lead">An error occurred while processing your request</p>
        <hr>
        <p>Please pass the following information to our tech team and they will ignore you</p>
    </div>

    <div>
        <h3>Error Details</h3>
        <div>
            <h5>Error Name: <code><s:property value="exception"/></code></h5>
            <h5>Error Code: <code><s:property value="exception.ErrorCode"/></code></h5>
        </div>

        <div style="margin-top: 50px;">
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <h3 class="panel-title">Stacktrace</h3>
                </div>
                <div class="panel-body">
                    <code>
                        <s:property value="exceptionStack" />
                    </code>
                </div>
            </div>
        </div>
    </div>

</div>