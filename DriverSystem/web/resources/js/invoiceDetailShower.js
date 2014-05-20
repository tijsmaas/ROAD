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
            var $tbody = $table.children("tbody:first");
            $tbody.empty();

            for (var i = 0; i < movements.length; i++) {
                var movement = movements[i];
                var date =  movement.movementDate.split("T");

                $tbody.append($("<tr>").append(
                        $("<td>").text(date[0] + " " + date[1])
                    ).append(
                        $("<td>").text(movement.cityName)
                    ).append(
                        $("<td>").text(movement.drivenKM +"km")
                    ).append(
                        $("<td>").text("€"+movement.totalCost)
                    ).append(
                        $("<td>").text("€"+movement.km_prijs)
                    )
                );
            }
            $table.show();
        },
        error: function (jqxhr, status, errothrown) {
            alert(errothrown);
        }
    })
}
