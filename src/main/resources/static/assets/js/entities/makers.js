$(function(){

    //  DELETING TITLE
    $('.my-maker-to-delete').on('click', function (e){
        e.preventDefault();

        var $maker = $(this);
        var makerId = $maker.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/makers/" + makerId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-maker-id="' + makerId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Maker #" + makerId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of maker #" + makerId);
            
            }
            
        });
        
    });

});
