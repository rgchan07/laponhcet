<!-- Custom styles for this template -->
<link href="guest/BRGY32/style.css" rel="stylesheet">

<body id="top" style="background:url(/static/BRGY32/images/background.jpg) no-repeat fixed center;overflow-x:hidden; ">
  <div class="wrapper row1" style="margin-top:45px;">
    <header id="header" class="clear">
        <!-- ################################################################################################ -->
        <div id="logo" class="fl_left">
            <img src="/static/BRGY32/images/logo-name.png" />
        </div>
        <div class="fl_right" style="margin-top:35px;">
            <form class="clear" method="post" action="#">
                <fieldset>
                    <input type="text" value="" placeholder="  Search anything here...">
                    <button type="submit" class="fa fa-search" title="Search"><em>Search</em></button>
                </fieldset>
            </form>
        </div>
        <!-- ################################################################################################ -->
    </header>
</div>
  <!-- ################################################################################################ -->
  <!-- ################################################################################################ -->
  <!-- ################################################################################################ -->
  <div class="wrapper row2">
    <div class="rounded">
        <nav id="mainav" class="clear">
            <!-- ################################################################################################ -->
            <ul class="clear navigation-menu">
                <li class="active"><a href="#" onclick="getCurrentPage('MAIN-PAGE')">Home</a></li>
                <li><a href="#" onclick="getCurrentPage('ABOUT')">About Us</a></li>
                <li><a href="#" onclick="getCurrentPage('OFFICIALS')">Officials</a></li>
                <li><a href="#" onclick="getCurrentPage('GALLERY')">Gallery</a></li>
                <li><a href="#" onclick="getCurrentPage('NEWS')">News & Events</a></li>
                <li><a class="view" target="_blank" href="/static/BRGY32/virtualtour/index.html">Virtual Tour</a></li>
                <li><a href="#contact_us">Contact Us</a></li>
            </ul>
            <!-- ################################################################################################ -->
        </nav>
    </div>
</div>

<div id="divMainPage"><jsp:include flush="true" page="pages/mainPage.jsp"></jsp:include></div>
<div id="divAbout"><jsp:include flush="true" page="pages/about.jsp"></jsp:include></div>
<div id="divOfficials"><jsp:include flush="true" page="pages/officials.jsp"></jsp:include></div>
<div id="divGallery"><jsp:include flush="true" page="pages/gallery.jsp"></jsp:include></div>
<div id="divNews"><jsp:include flush="true" page="pages/news.jsp"></jsp:include></div>

  <div class="wrapper row4">
    <div class="rounded">
        <footer id="footer" class="clear">
            <!-- ################################################################################################ -->
            <div class="one_third first">
                <figure class="center"><img class="btmspace-15" src="/static/BRGY32/images/worldmap.png" alt="">
                    <figcaption><a href="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3920.9137084944487!2d122.94686011425553!3d10.663805864179599!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x33aed1b5d3ffffff%3A0x33adb2e1399dd1a2!2sBarangay+32+Barangay+Hall+-+Bacolod+City!5e0!3m2!1sen!2sph!4v1523602786506" target="_blank">Find Us With Google Maps &raquo;</a></figcaption>
                </figure>
            </div>
            <div class="one_third">
                <address id="contact_us">
                    <b>Barangay 32 Hall</b>
                    <br> Brgy. 32 (Pob.), Bacolod City
                    <br> Negros Occidental 6100
                    <br>
                    <br>
                    <i class="fa fa-fax pright-10"></i>704-6746
                    <br>
                    <i class="fa fa-envelope pright-10"></i><a target="_blank" href="http://www.bcdbarangay32@gmail.com/">bcdbarangay32@gmail.com</a>
                    <br>
                     <i class="fa fa-envelope pright-10"></i><a target="_blank" href="http://www.brgy32bacolod@gmail.com/">brgy32bacolod@gmail.com</a>
                </address>
            </div>
            <div class="one_third">
               <p class="nospace btmspace-10"><b>Have a glimpse of our place through:</b></p>
             <a class="view" target="_blank" href="/static/BRGY32/virtualtour/index.html"><img src="/static/BRGY32/images/virtualtour.png"></a>
            </div>
            <!-- ################################################################################################ -->
        </footer>
    </div>
</div>
  <!-- ################################################################################################ -->
  <!-- ################################################################################################ -->
  <!-- ################################################################################################ -->
 <div class="wrapper row5">
    <div id="copyright" class="clear">
        <!-- ################################################################################################ -->
        <p class="fl_left">Copyright &copy; 2017 - All Rights Reserved</p>
        <p class="fl_right">Powered by <a target="_blank" href="http://www.technopal.com/" title="Technopal Software">Technopal Software</a></p>
        <!-- ################################################################################################ -->
    </div>
    </div>

<script src="/static/BRGY32/layout/scripts/jquery.min.js"></script>
<script src="/static/BRGY32/layout/scripts/jquery.fitvids.min.js"></script>
<script src="/static/BRGY32/layout/scripts/jquery.mobilemenu.js"></script>
<script src="/static/BRGY32/layout/scripts/tabslet/jquery.tabslet.min.js"></script>
<script src="/static/BRGY32/layout/scripts/nivo-lightbox/nivo-lightbox.min.js"></script>
</body>


<script>
hidePages();

document.getElementById('divMainPage').style.display = "block";
function getCurrentPage(pageName){
	hidePages();
	if(pageName=='MAIN-PAGE'){
		document.getElementById('divMainPage').style.display = "block";
	}else if(pageName=='ABOUT'){
		document.getElementById('divAbout').style.display = "block";
	}else if(pageName=='OFFICIALS'){
		document.getElementById('divOfficials').style.display = "block";
	}else if(pageName=='GALLERY'){
		document.getElementById('divGallery').style.display = "block";
	}else if(pageName=='NEWS'){
		document.getElementById('divNews').style.display = "block";		
	}
}

function hidePages(){
	document.getElementById('divMainPage').style.display = "none";
	document.getElementById('divAbout').style.display = "none";
	document.getElementById('divOfficials').style.display = "none";
	document.getElementById('divGallery').style.display = "none";
	document.getElementById('divNews').style.display = "none";
}

$('.navigation-menu').on('click','li', function(){
	   $(this).addClass('active').siblings().removeClass('active');
	});
</script>

   