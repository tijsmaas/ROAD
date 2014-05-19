/**
 * Created by Niek on 19/05/14.
 */
function showVehicleInvoiceDetails(vehicleInvoiceID) {
    alert(vehicleInvoiceID);
    $.ajax({
        type: "GET",
        url: "webapi/invoiceAPI/details/" + vehicleInvoiceID,
        contentType: "application/json",
        dataType: "json",
        success: function (movements) {
            alert('success');
        },
        error: function (jqxhr, status, errothrown) {
            alert(errothrown);
        }
    })
}
