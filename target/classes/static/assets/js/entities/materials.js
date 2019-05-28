$(function(){

    //  DELETING MATERIAL
    $('.my-material-to-delete').on('click', function (e){
        e.preventDefault();

        var $material = $(this);
        var materialId = $material.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/materials/" + materialId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-material-id="' + materialId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Material #" + materialId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of material #" + materialId);
            
            }
            
        });
        
    });

});
