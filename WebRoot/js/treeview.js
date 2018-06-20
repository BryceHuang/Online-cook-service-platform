$(function(){
     $(".toggle dl dd").hide();
     $(".toggle dl dt").click(function(){
     $(".toggle dl dd").not($(this).next()).hide();
     $(".toggle dl dt").not($(this).next()).removeClass("current");
     $(this).next().slideToggle(500);
     $(this).toggleClass("current");
     });
    });