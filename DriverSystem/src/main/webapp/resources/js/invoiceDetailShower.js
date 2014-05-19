/**
 * Created by Niek on 19/05/14.
 */
function showVehicleInvoiceDetails(vehicleInvoiceID) {

    $("#detailModal").modal();


    var $modalBody = $("#modalbody");

    var $table = $modalBody.find("table:first");

    //$table.find("tbody:first").clear();

    $table.hide();
    $.ajax({
        type: "GET",
        url: "webapi/invoiceAPI/details/" + vehicleInvoiceID,
        contentType: "application/json",
        dataType: "json",
        success: function (movements) {
            //$modalBody.text('success');

            $table.show();
        },
        error: function (jqxhr, status, errothrown) {
            alert(errothrown);
        }
    })
}
