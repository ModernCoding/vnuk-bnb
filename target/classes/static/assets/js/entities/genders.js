$(function(){
    //  DELETING GENDER
    $('.my-gender-to-delete').on('click', function (e){
        e.preventDefault();

        var $gender = $(this);
        var genderId = $gender.val();

        $.ajax({
            type: "DELETE",
            url: "/genders/" + genderId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-gender-id="' + genderId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Gender #" + genderId + " has successfully been deleted.");
            },
            
            error: function() {
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of gender #" + genderId);
            }
            
        });
        
    });

});
