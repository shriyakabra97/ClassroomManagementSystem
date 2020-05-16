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

    $.validator.addMethod( "mypattern", function( value, element ) {
        return this.optional( element ) || /(A|R)([1-3])([0-1])([1-9])/i.test( value );
    }, "Please follow the Class Name conventions" );


    $("#addclass-form").validate({
        rules: {
            classCode:{
                nowhitespace: true,
                alphanumeric : true,
                mypattern : true,
                required: true

            },
            capacity:{
                min: 50,
                max : 250,
                required:true
            },
            plugs:{
                min: 0,
                required: true

            }

        },
        messages:{

        }
    })
});