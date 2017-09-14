<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">

    <div class="row">
        <h1 class="title">iHotel Problem Patterns List</h1>

        <div class="vertical-tab-container">
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 vertical-tab-menu">

                <div class="list-group">

                    <s:iterator value="problemList">
                        <a href="#" class="list-group-item text-center">
                            <h4 class="fa fa-<s:property value="icon" />"></h4><br/><s:property value="name"/>
                        </a>
                    </s:iterator>
                </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 vertical-tab">

                <s:iterator value="problemList">
                    <div class="vertical-tab-content">
                        <h3 class="fa fa-<s:property value="icon" />"style="color:#bfa145"></h3>
                        <h4><s:property value="name"/> </h4>
                        <p><s:property value="description"/> </p>
                        <hr/>
                        <p><strong>Enabled: </strong>
                            <span id="problem-status-<s:property value="id"/>">
                                <s:property value="enabled"/>
                            </span>
                        </p>
                        <s:checkbox name="enabled" data-toggle="toggle" onchange="config.changeStatus(this)" data-stat="%{enabled}" data-id="%{id}"/>

                    </div>
                </s:iterator>

            </div>
        </div>
    </div>
</div>