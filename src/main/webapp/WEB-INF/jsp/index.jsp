<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap/bootstrap-theme.min.css" />">
    
    <link rel="stylesheet" href="<c:url value="/res/css/datatables/datatables.min.css" />" >
    <link rel="stylesheet" href="<c:url value="/res/css/datatables/datatables.bootstrap.min.css" />" >
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap/bootstrap-datetimepicker.css" />" >
        
    <link rel="stylesheet" href="<c:url value="/res/css/styles.css" />">

    <title>Parts filter</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <h1 align="center">Parts Filtering Example</h1>
        <br>
        <div class="col-md-3">
            <div class="panel panel-primary">
                <div class="panel-heading">Filter</div>
                <div class="panel-body">
                    <form method="POST" action="<c:url value='/' />">
                        <table class="table table-condensed">
                            <tbody>
                                <tr><td><b>Part name</b></td></tr>
                                <tr><td><input type="text" name="partname" class="form-control"></td></tr>
                                
                                <tr><td><b>Part number</b></td></tr>
                                <tr><td><input type="text" name="partnumber" class="form-control"></td></tr>
                                
                                <tr><td><b>Vendor</b></td></tr>
                                <tr><td><input type="text" name="vendor" class="form-control"></td></tr>
                                
                                <tr><td><b>Quantity</b></td></tr>
                                <tr><td><input type="text" name="qty" class="form-control"></td></tr>
                                
                                <tr><td><b>Shipped</b></td></tr>
                                <tr><td>-after</td></tr>
                                <tr><td><input type="text" name="shAfter" id="shAfter" class="form-control"></td></tr>
                                <tr><td>-before</td></tr>
                                <tr><td><input type="text" name="shBefore" id="shBefore" class="form-control"></td></tr>
                                
                                <tr><td><b>Recieved</b></td></tr>
                                <tr><td>-after</td></tr>
                                <tr><td><input type="text" name="reAfter" id="reAfter" class="form-control"></td></tr>
                                <tr><td>-before</td></tr>
                                <tr><td><input type="text" name="reBefore" id="reBefore" class="form-control"></td></tr>
                            </tbody>
                        </table>
                        
                        <input id="send" type="submit" name="send" value="Filter" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
        
        <div class="col-md-9">

            <div class="panel panel-primary">
                <div class="panel-heading">Parts list</div>
                <div class="panel-body">
                    <table class="table table-condensed, table-hover" id="partstable">
                        <thead>
                            <th>Part name</th>
                            <th>Part number</th>
                            <th>Vendor</th>
                            <th>Quantity</th>
                            <th>Shipped</th>
                            <th>Recieved</th>
                        </thead>
                        <c:forEach items="${partsList}" var="part">
                            <tr>
                                <td><c:out value="${part.partName}"/></td>
                                <td><c:out value="${part.partNumber}"/></td>
                                <td><c:out value="${part.vendor}"/></td>
                                <td><c:out value="${part.qty}"/></td>
                                <td><c:out value="${part.shipped.toString('MMM dd, yyyy')}"/></td>
                                <td><c:out value="${part.recieve.toString('MMM dd, yyyy')}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
    
    
<script src="<c:url value="/res/js/jquery/jquery-3.2.1.min.js" /> "></script>
<script src="<c:url value="/res/js/moment/moment.min.js" /> "></script>
<script src="<c:url value="/res/js/bootstrap/bootstrap.min.js" />"></script>
<script src="<c:url value="/res/js/datatables/datatables.min.js" />"></script>
<script src="<c:url value="/res/js/datatables/datatables.bootstrap.min.js" />"></script>
<script src="<c:url value="/res/js/bootstrap/bootstrap-datetimepicker.min.js" />"></script>

<script type="text/javascript">
    $(function(){
        $("#partstable").DataTable();
        
        $("#shAfter").datetimepicker({
            locale: 'en',
            format: 'MMM DD, YYYY'
        });
        
        $("#shBefore").datetimepicker({
            locale: 'en',
            format: 'MMM DD, YYYY'
        });
        
        $("#reAfter").datetimepicker({
            locale: 'en',
            format: 'MMM DD, YYYY'
        });
        
        $("#reBefore").datetimepicker({
            locale: 'en',
            format: 'MMM DD, YYYY'
        });
    });
</script>

</body>



</html>
