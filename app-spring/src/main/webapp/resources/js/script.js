$(".error").hide();
$(".success").hide();

<!-- Bootstrap Modal JavaScript for login form -->
$("#myBtn").click(function(){
    $("#myModal").modal();
});

$( "#fill-manager" ).click(function() {
    $( "#name" ).val( "Vasya" );
    $( "#surname" ).val( "Pupkin" );
    $( "#username" ).val( "aloginov" );
    $( "#password" ).val( "1234" );
});

$( "#fill-driver" ).click(function() {
    $( "#name" ).val( "Vasya" );
    $( "#surname" ).val( "Pupkin" );
    $( "#username" ).val( "gchichvarkin" );
    $( "#password" ).val( "1234" );
});

function validateLoginForm() {
    var errorHTML;
    var email = document.forms["loginForm"]["username"].value;
    var password = document.forms["loginForm"]["password"].value;

    if (email == null || email == "") {
        errorHTML = "Username must be filled out";
        $(".error").html(errorHTML).show();
        document.getElementById("username").style.border = "2px solid red";
        return false;
    } else if (email != "") {
        $(".error").hide();
        document.getElementById("username").style.border = "2px solid #3f8abf";
    }

    if (password == null || password == "") {
        errorHTML = "Password must be filled out";
        $(".error").html(errorHTML).show();
        document.getElementById("password").style.border = "2px solid red";
        return false;
    } else if (password != "") {
        $(".error").hide();
        document.getElementById("password").style.border = "2px solid #3f8abf";
    }
    $('button[type="submit"]').hide();
    var successHTML = "Processing...";
    $(".success").html(successHTML).show();
}

function validateRegForm() {
    var errorHTML;
    var name = document.forms["regForm"]["name"].value;
    var surname = document.forms["regForm"]["surname"].value;
    var email = document.forms["regForm"]["email"].value;
    var password = document.forms["regForm"]["password"].value;

    if (name == null || name == "") {
        errorHTML = "Name must be filled out";
        $(".error").html(errorHTML).show();
        document.getElementById("name").style.border = "2px solid red";
        return false;
    } else if (name != "") {
        $(".error").hide();
        document.getElementById("name").style.border = "2px solid #3f8abf";
    }

    if (surname == null || surname == "") {
        errorHTML = "Surname must be filled out";
        $(".error").html(errorHTML).show();
        document.getElementById("surname").style.border = "2px solid red";
        return false;
    } else if (surname != "") {
        $(".error").hide();
        document.getElementById("surname").style.border = "2px solid #3f8abf";
    }

    if (email == null || email == "") {
        errorHTML = "Email must be filled out";
        $(".error").html(errorHTML).show();
        document.getElementById("email").style.border = "2px solid red";
        return false;
    } else if (email != "") {
        $(".error").hide();
        document.getElementById("email").style.border = "2px solid #3f8abf";
    }

    if (password == null || password == "") {
        errorHTML = "Password must be filled out";
        $(".error").html(errorHTML).show();
        document.getElementById("password").style.border = "2px solid red";
        return false;
    } else if (password != "") {
        $(".error").hide();
        document.getElementById("password").style.border = "2px solid #3f8abf";
    }
    $('input[type="submit"]').hide();
    var successHTML = "Processing...";
    $(".success").html(successHTML).show();
}