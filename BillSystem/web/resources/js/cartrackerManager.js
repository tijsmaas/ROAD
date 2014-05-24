/**
 * Created by Niek on 24/05/14.
 */

var CARSELECTOR_ID = "selectCar";
var availableUsers = null;
var currentVehicleID = null;

/**
 * On document ready, bind the event handlers.
 */
$(document).ready(function () {
    var $carSelector = $("#" + CARSELECTOR_ID);


    //EVENT HANDLERS

    var $userSelector = $("#userSelectDetails");
    if (availableUsers != null) {
        fillUsersSelector($userSelector, availableUsers);
    } else {
        getUsers(function (userList) {
            availableUsers = userList;
            fillUsersSelector($userSelector, userList);
        });
    }

    var selectedCarID = getSelectedValue($carSelector);
    loadCarDetails(selectedCarID);

    $carSelector.change(function () {
        loadCarDetails(getSelectedValue($(this)));
    });

    $("#btnAddVehicle").click(function () {
        addNewVehicle();
    });

    $("#btnNewVehicleDialog").click(function () {
        newVehicleDialog();

        return false;
    });

    $("#btnChangeOwner").click(function(){
       changeCurrentOwner();

        return false;
    });


});


/**
 * Get the selected value of a select element
 * @param $select the select element to get the selected option of
 * @returns {*}
 */
function getSelectedValue($select) {
    return $select.find('option:selected').val();
}

/**
 * Load the details for a certain car
 * @param carID The ID of the car for which we want to load details
 */
function loadCarDetails(carID) {
    $.ajax({
        type: "GET",
        url: "webapi/CartrackerAPI/vehicle/" + carID,
        contentType: "application/json",
        dataType: "json",
        success: function (vehicleData) {
            currentVehicleID = vehicleData.vehicleID;
            $("#lblLicensePlate").text("License plate: " + vehicleData.licensePlate);

            $("#lblId").text("Cartracker ID: " + vehicleData.cartrackerID);

            $("#userSelectDetails").val(vehicleData.vehicleOwner.user.id);

            $("#changeOwnerSuccess").hide();
            $("#changeOwnerFailed").hide();
            $("#vehicleDetails").show();
        },
        error: function (jqxhr, e, errorthrown) {

            alert(errorthrown);
        }
    });
}

/**
 * Change the current owner of selected vehicle.
 */
function changeCurrentOwner(){
    if(currentVehicleID == null){
        return;
    }

    var $ownerSelector = $("#userSelectDetails");
    var newOwnerID = getSelectedValue($ownerSelector);

    var newOwnerHelper = {};
    newOwnerHelper.vehicleID = currentVehicleID;
    newOwnerHelper.newOwnerID = newOwnerID;
    $.ajax({
        type: "POST",
        url: "webapi/CartrackerAPI/updateVehicleOwner",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(newOwnerHelper),
        success: function (newOwnership) {
            $("#changeOwnerSuccess").show();
            $("#changeOwnerFailed").hide();
        },
        error: function (jqxhr, e, errorthrown) {
            $("#changeOwnerSuccess").hide();
            $("#changeOwnerFailed").show();
        }
    });

}

/**
 * Show the dialog for adding a new vehicle.
 */
function newVehicleDialog() {
    $("#newVehicleModal").modal();

    var $userSelector = $("#newVehicleSelectUser");
    if (availableUsers != null) {
        fillUsersSelector($userSelector, availableUsers);
    } else {
        getUsers(function (userList) {
            availableUsers = userList;
            fillUsersSelector($userSelector, userList);
        });
    }
}

/**
 * Get all users to add to the select
 * @param onSuccessCallback the callback for when the ajax request is successful
 */
function getUsers(onSuccessCallback) {
    $.ajax({
        type: "GET",
        url: "webapi/CartrackerAPI/users",
        contentType: "application/json",
        success: function (users) {
            onSuccessCallback(users);
        },
        error: function (jqxhr, status, errothrown) {
            alert(errothrown);
        }
    })
}

/**
 * Fill the users selector with a list of users
 * @param $selectItem the item to fill
 * @param userList the list of users to add
 */
function fillUsersSelector($selectItem, userList) {
    $selectItem.empty();
    for (var i = 0; i < userList.length; i++) {
        var user = userList[i];

        $selectItem.append($("<option>").val(user.id).text(user.userName))
    }
}

/**
 * Add a new vehicle to the datbase
 */
function addNewVehicle() {
    var cancel = false;
    $("#newVehicleForm").find('input, select').each(function () {
        if ($(this).val() == "") {
            $(this).parent().parent().addClass('has-error');
            cancel = true;
        } else {
            $(this).parent().parent().removeClass('has-error');
        }
    });
    if(cancel) return;


    var carTrackerID = $("#txtCarTrackerID").val();
    var licensePlate = $("#txtLicensePlate").val();
    var ownerID = $("#newVehicleSelectUser").find("option:selected").val();

    var newVehicle = {};
    newVehicle.licensePlate = licensePlate;
    newVehicle.cartrackerID = carTrackerID;
    newVehicle.movementUserID = ownerID;

    $.ajax({
        type: "POST",
        url: "webapi/CartrackerAPI/addVehicle",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(newVehicle),
        success: function (addedVehicle) {
            $("#successAdd").show();

            $("#newVehicleModal").modal('hide');

            $("#newVehicleForm").find('input').val("");
            appendNewVehicleToSelect(addedVehicle);


        },
        error: function (jqxhr, e, errorthrown) {
            $("#successAdd").hide();
            alert(errorthrown);
        }
    });
}

/**
 * Append a new vehicle to the vehicle selector
 * @param vehicle the vehicle toappend
 */
function appendNewVehicleToSelect(vehicle){
        $("#selectCar").append($("<option>").val(vehicle.vehicleID).text(vehicle.licensePlate));
}


