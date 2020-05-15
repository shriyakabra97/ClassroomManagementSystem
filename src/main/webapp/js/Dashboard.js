
$(function () {

    //below commented methods are just for reference, we are able to access these using CDN.

    // $.validator.addMethod( "nowhitespace", function( value, element ) {
    //     return this.optional( element ) || /^\S+$/i.test( value );
    // }, "No white space please" );
    //
    // $.validator.addMethod( "alphanumeric", function( value, element ) {
    //     return this.optional( element ) || /^\w+$/i.test( value );
    // }, "Letters, numbers, and underscores only please" );

    // $.validator.addMethod( "lettersonly", function( value, element ) {
    //     return this.optional( element ) || /^[a-z]+$/i.test( value );
    // }, "Letters only please" );



    //below are customised Methods..

    $.validator.addMethod( "timeGreaterThan", function( value, element , param) {
        var target = $(param);
        console.log("hereee in time greater than");
        if ( this.settings.onfocusout && target.not( ".validate-greaterThan-blur" ).length ) {
            target.addClass( "validate-greaterThan-blur" ).on( "blur.validate-greaterThan", function() {
                $( element ).valid();
            } );
        }
        return value > target.val();
    }, "Please enter a greater value." );

    $.validator.addMethod("minDateToday", function (value, element) {
        var target = new Date();
        var reqDate = new Date($("#datepicker").val());
        return reqDate >= target;

    }, "Please enter valid date");

    $.validator.setDefaults({
        errorClass: 'help-block',
        highlight: function(element) {
            $(element)
                .closest('.form-group')
                .addClass('has-error');
        },
        unhighlight: function(element) {
            $(element)
                .closest('.form-group')
                .removeClass('has-error');
        }

    });

    $("#dashboard-form").validate({
        rules: {

            startTime:{
              time: true
            },

            endTime :{
                timeGreaterThan: "#startTime",
                time : true
            },
            capacity:{
                min: 0
            },
            datepicker:{
                // min:
                minDateToday: true
            },
            plugs:{
                min:0
            },
            cleanCheck:{
                required: false
            },
            projectorCheck:{
                required: false
            }

        },
        messages: {
            capacity: {
                min: "Please enter a positive value"
            }

        }
    })



});
