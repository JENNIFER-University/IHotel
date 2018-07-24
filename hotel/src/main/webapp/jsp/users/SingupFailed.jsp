<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">

    <h2>Singup Failed</h2>

    <s:if test="hasActionErrors()">
        <div class="alert alert-danger">
            <p><s:actionerror/></p>
        </div>
    </s:if>


    <div class="row">
        <div class="panel panel-daner">
            <div class="panel-heading">Error!</div>

            <div class="panel-body">
                <p>Error creating your account. <a href="signup">Please try again</a></p>
            </div>
        </div>
    </div>


</div>