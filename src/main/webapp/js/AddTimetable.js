$(function () {

    //below methods are just for reference, we are able to access these using CDN.

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

    $.validator.addMethod( "timeGreaterThanInTimetable", function(value, element , param) {
        var target = $(param);
        console.log("hereee in time greater than");
        if ( this.settings.onfocusout && target.not( ".validate-greaterThan-blur" ).length ) {
            target.addClass( "validate-greaterThan-blur" ).on( "blur.validate-greaterThan", function() {
                $( element ).valid();
            } );
        }
        return value > target.val();
    }, "Please enter a greater value." );

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


    $("#addtimetable-form").validate({
        rules: {
            startTime:{
                time: true
            },

            endTime :{
                timeGreaterThanInTimetable: "#startTime",
                time : true
            }


        }
    })
});