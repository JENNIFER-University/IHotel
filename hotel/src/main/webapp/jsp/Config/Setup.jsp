<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">

    <h1 class="title">iHotel Setup</h1>

    <div class="alert alert-info">iHotel configuration is not set. Please set the cofigurations below</div>

    <div class="room-features">

        <div class="row">

            <form action="doSetup" method="post">

                <div class="panel panel-default">

                    <div class="panel-heading">iHotel Configurations</div>

                    <div class="panel-body">

                        <div class="form-group">
                            <label>iPayment IP: <i class="fa fa-info-circle" data-toggle="tooltip" title="Enter iPayment IP Address: Example: 127.0.0.1"></i> </label>
                            <input type="text" class="form-control" id="ipaymentIp" name="ipaymentIp" value="${ipaymentIp}">
                        </div>

                        <div class="form-group">
                            <label>iPayment Port: <i class="fa fa-info-circle" data-toggle="tooltip" title="Enter iPayment Port Number: Example: 18080"></i>  </label>
                            <input type="text" class="form-control" id="ipaymebtPort" name="ipaymebtPort" value="${ipaymebtPort}">
                        </div>

                    </div>

                    <div class="panel-footer">
                        <button id="bookNow" type="submit" class="btn btn-default btn-lg">
                            <i class="fa fa-floppy-o" aria-hidden="true"></i> Save Configuration
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>

