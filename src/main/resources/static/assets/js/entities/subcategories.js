$(function(){

    //  DELETING SUBCATEGORY
    $('.my-subcategory-to-delete').on('click', function (e){
        e.preventDefault();

        var $subcategory = $(this);
        var subcategoryId = $subcategory.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/subcategories/" + subcategoryId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-subcategory-id="' + subcategoryId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Subcategory #" + subcategoryId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of subcategory #" + subcategoryId);
            
            }
            
        });
        
    });

});
