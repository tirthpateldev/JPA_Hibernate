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

 /*   $('#Add-Row').on('click', function() {
        $("#lazyList").valid();
    });*/


    // $("#lazyList").validate();
 /*   $('#lazyList').validate().find('[name^="operationParameterse"]').each(function() {

        $(this).rules('add', { required : true, minlength: 2 });

    });*/

});


$(document).ready(function () {
    /*$("#lazyList")*/

  /*     $('#lazyList').find('[name^="operationParameterse"]').each(function() {

      $(this).rules('add', { required : true, minlength: 2 });

   });*/

    /*$('#lazyList').find('[name^="operationParameterse"]').each(function() {

        $(this).rules('add', { required : true, minlength: 2 });

    });*/
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
     /*   messages: {
            name: {
                required: "Please enter a username",
                minlength: "Your username must consist of at least 2 characters"
            },
            username1: {
                required: "Please enter a username",
                minlength: "Your username must consist of at least 2 characters"
            },
            email1: "Please enter a valid email address",
        },*/
        /*errorElement: "em",
        errorPlacement: function (error, element) {
            // Add the `help-block` class to the error element
            error.addClass("help-block");

            // Add `has-feedback` class to the parent div.form-group
            // in order to add icons to inputs
            element.parents(".col-sm-5").addClass("has-feedback");

            if (element.prop("type") === "checkbox") {
                error.insertAfter(element.parent("label"));
            } else {
                error.insertAfter(element);
            }

            // Add the span element, if doesn't exists, and apply the icon classes to it.
            if (!element.next("span")[0]) {
                $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>")
                    .insertAfter(element);
            }
        },
        success: function (label, element) {
            // Add the span element, if doesn't exists, and apply the icon classes to it.
            if (!$(element).next("span")[0]) {
                $("<span class='glyphicon glyphicon-ok form-control-feedback'></span>")
                    .insertAfter($(element));
            }
        },
        highlight: function (element, errorClass, validClass) {
            $(element).parents(".col-sm-5").addClass("has-error").removeClass("has-success");
            $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parents(".col-sm-5").addClass("has-success").removeClass("has-error");
            $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
        }*/
    });
    $(".table").rules("add", {
        required: true,
        minlength: 3
    });
});

/*.find('[name^="operationParameterse"]').each(function() {
    $(this).rules("add", { required : true, minlength: 2 });
});*/


</script>