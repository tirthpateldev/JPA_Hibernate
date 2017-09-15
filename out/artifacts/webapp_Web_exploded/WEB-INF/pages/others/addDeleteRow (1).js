// $(document).ready(function() {
  $("#add").click(function() {
    var intId = $("#buildyourform div").length + 1;
    var fieldWrapper = $("<div class=\"fieldwrapper\" id=\"field" + intId + "\"/>");
    var fName = $("<input type=\"text\" class=\"fieldname\" />");
    var fType = $("<select class=\"fieldtype\"><option value=\"checkbox\">Checked</option><option value=\"textbox\">Text</option><option value=\"textarea\">Paragraph</option></select>");
    var removeButton = $("<input type=\"button\" class=\"remove\" value=\"-\" />");
    removeButton.click(function() {
      $(this).parent().remove();
    });
    fieldWrapper.append(fName);
    fieldWrapper.append(fType);
    fieldWrapper.append(removeButton);
    $("#buildyourform").append(fieldWrapper);
  });
  $("#preview").click(function() {
    $("#yourform").remove();
    var fieldSet = $("<fieldset id=\"yourform\"><legend>Your Form</legend></fieldset>");
    $("#buildyourform div").each(function() {
      var id = "input" + $(this).attr("id").replace("field", "");
      var label = $("<label for=\"" + id + "\">" + $(this).find("input.fieldname").first().val() + "</label>");
      var input;
      switch ($(this).find("select.fieldtype").first().val()) {
        case "checkbox":
          input = $("<input type=\"checkbox\" id=\"" + id + "\" name=\"" + id + "\" />");
          break;
        case "textbox":
          input = $("<input type=\"text\" id=\"" + id + "\" name=\"" + id + "\" />");
          break;
        case "textarea":
          input = $("<textarea id=\"" + id + "\" name=\"" + id + "\" ></textarea>");
          break;
      }
      fieldSet.append(label);
      fieldSet.append(input);
    });
    $("body").append(fieldSet);
  });
// });
