$(function(){

    //  DELETING CATEGORY
    $('.my-room-type-to-delete').on('click', function (e){
        e.preventDefault();

        var $roomType = $(this);
        var roomTypeId = $roomType.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/room-types/" + roomTypeId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-room-type-id="' + roomTypeId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Room Types #" + roomTypeId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of room types #" + roomTyeId);
            
            }
            
        });
        
    });

});