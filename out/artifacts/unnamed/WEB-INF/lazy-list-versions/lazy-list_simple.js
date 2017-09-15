<script type="text/javascript">

    function addRow(tableID) {

        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell1 = row.insertCell(0);
        var element1 = document.createElement("input");
        element1.type = "checkbox";
        element1.name = "chkbox[]";
        cell1.appendChild(element1);

        var cell2 = row.insertCell(1);
        cell2.innerHTML = rowCount + 1;

        var cell3 = row.insertCell(2);
        var element2 = document.createElement("input");
        element2.type = "text";
        var length = (table.rows.length) - 1;
        // element2.setAttribute("name","username1" );
        element2.name = "operationParameterses[" + length + "].name";
        cell3.appendChild(element2);

    }

function deleteRow(tableID) {
    try {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;

        for (var i = 0; i < rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if (null !== chkbox && true === chkbox.checked) {
                table.deleteRow(i);
                rowCount--;
                i--;
            }
        }
    } catch (e) {
        alert(e);
    }
}

/*
       $.validator.setDefaults({
           submitHandler: function () {
               alert("submitted!");
           }
       });
       */








$(document).ready(function () {


    $('#lazyList').validate({

        rules: {
            name: {
                required: true,
                minlength: 2
            },
            username1: {
                required: true,
                minlength: 2
            },
            email1: {
                required: true,
                email: true
            }
        }
    });
    $(".table").rules("add", {
        required: true,
        minlength: 3
    });
});



</script>