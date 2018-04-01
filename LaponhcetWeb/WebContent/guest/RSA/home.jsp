<div class="navbar-wrapper">
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Recoletos School of Agriculture</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a id="nav-button" class="page-scroll" href="#page-top">Home</a></li>
					<li><a id="nav-button" class="page-scroll" href="#aboutUs">About Us</a></li>
					<li><a id="nav-button" class="page-scroll" href="#gallery">Gallery</a></li>
					<li><a id="nav-button" class="page-scroll" href="#events">Events</a></li>
					<li><a id="nav-button" class="page-scroll" href="#sme">SME</a></li>
					<li><a id="nav-button" class="page-scroll" href="#farms">Farms</a></li>
					<li><a id="nav-button" class="page-scroll" href="#timeline">Timeline</a></li>
					<li><a id="nav-button" class="page-scroll" href="#register">Register</a></li>
					<li><a id="nav-button" class="page-scroll" href="#contactUs">Contact Us</a></li>
					<li><a data-toggle="modal" href="#modal-form">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>

<div id="modal-form" class="modal fade" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="row">
					<div class="col-sm-6 b-r">
						<h3 class="m-t-none m-b">Sign in</h3>
						<p>Sign in today for more experience.</p>
						<div class="form-group">
							<label>User Name</label> <input name="txtUsernName" type="text"
								placeholder="User Name" class="form-control">
						</div>
						<div class="form-group">
							<label>Password</label> <input name="txtPassword" type="password"
								placeholder="Password" class="form-control">
						</div>
						<div>
							<button class="btn btn-sm btn-primary pull-right m-t-n-xs"
								type="submit" onclick="openLink('G00002')">
								<strong>Log in</strong>
							</button>
							<label> <input type="checkbox" class="i-checks">
								Remember me
							</label>
						</div>
					</div>
					<div class="col-sm-6">
						<h4>Not yet a member?</h4>
						<p>Please Register</p>
						<p class="text-center">
							<a href="#register"
								onblur="$('#modal-form').modal('hide');$('.modal-backdrop').remove();"><i
								class="fa fa-sign-in big-icon"></i></a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="inSlider" class="carousel carousel-fade" data-ride="carousel">
	<ol class="carousel-indicators">
		<li data-target="#inSlider" data-slide-to="0" class="active"></li>
		<li data-target="#inSlider" data-slide-to="1"></li>
		<li data-target="#inSlider" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<div class="container">
				<div class="carousel-caption">
					<h1>
						RECOLETOS SCHOOL OF<br>
						<b style="font-size: 2em">AGRICULTURE</b>
					</h1>
					<p style="letter-spacing: 8.5px;">Agriculture of the 21st
						Century.</p>
					<p>
						<a class="btn btn-lg btn-primary" href="" target="_blank"
							role="button">VIEW PROGRAMS</a>
					</p>
				</div>
				<div class="carousel-image-logo wow zoomIn">
					<img src="static/RSA/images/logo.png" alt="logo" width="450" />
				</div>
			</div>
			<!-- Set background for slide in css -->
			<div class="header-back one"></div>

		</div>
		<div class="item">
			<div class="container">
				<div class="carousel-caption blank">
					<h1>
						VIRTUAL FARM<br> The modern way to experience<br>
						agricultural farming at its best!
					</h1>
					<p>
						Provides a virtual world to experience "real-life" farming <br>
						to encourage and develop entrepreneurs and farmers to produce <br>
						healthy, quality and organic crops and livestock. Want to know
						how?
					</p>
					<p>
						<a class="btn btn-lg btn-primary" href="" target="_blank"
							role="button">Virtual Farm Tour</a>
					</p>
				</div>
			</div>
			<div class="carousel-image wow zoomIn">
				<img src="static/RSA/images/slide1.png" alt="slide1" />
			</div>
			<!-- Set background for slide in css -->
			<div class="header-back two"></div>
		</div>
		<div class="item">
			<div class="container">
				<div class="carousel-caption blank">
					<h1>
						Farming Classes Online<br /> Learn new farming techniques <br>whenever
						&amp; wherever you are!
					</h1>
					<p>
						Enroll in our Virtual Classroom where you can attend <br>
						online classes by just using your personal computer or laptops. <br>
						Interact with our online teacher using chat and video calls.
					</p>
					<p>
						<a class="btn btn-lg btn-primary" href="" target="_blank"
							role="button">Learn more</a>
					</p>
				</div>
			</div>
			<div class="carousel-image wow zoomIn">
				<img src="static/RSA/images/slide2.png" alt="slide2" />
			</div>
			<!-- Set background for slide in css -->
			<div class="header-back three"></div>
		</div>
	</div>
	<a class="left carousel-control" href="#inSlider" role="button"
		data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
		aria-hidden="true"></span> <span class="sr-only">Previous</span>
	</a> <a class="right carousel-control" href="#inSlider" role="button"
		data-slide="next"> <span class="glyphicon glyphicon-chevron-right"
		aria-hidden="true"></span> <span class="sr-only">Next</span>
	</a>
</div>
<jsp:include page="bibleVerse.jsp"></jsp:include>

<div class="container">
	<div class="col-lg-12">
		<div class="wrapper wrapper-content animated fadeInUp">
			<div class="ibox">
				<div class="ibox-content text-center">
					<h2>
						<span class="navy">Inspirational Message</span>
					</h2>
				</div>
				<div class="col-lg-4">
					<div class="col-sm-5">
						<div class="text-center">
							<img alt="image" class="img-circle m-t-xs img-responsive"
								src="static/inspinia/img/a31.jpg">
						</div>
					</div>
					<div class="social-body text-justify">President message here
						president message here president message here. President message
						here president message here president message here. President
						message here president message here president message here.
						President message here president message here president message
						here. President message here president message here president
						message here. President message here president message here
						president message here. President message here president message
						here president message here. President message here president
						message here president message here. President message here
						president message here president message here.</div>
					<div class="col-sm-12 text-right">
						<h3>
							<strong>Rev. Fr. Eduardo S. Celiz, Jr.</strong>
						</h3>
						<div class="m-t-xs font-bold">President, UNO-Recoletos</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="col-sm-5">
						<div class="text-center">
							<img alt="image" class="img-circle m-t-xs img-responsive" src="">
						</div>
					</div>
					<div class="social-body text-justify">Vice President message
						here Vice President here Vice President here. Vice President
						message here Vice President here Vice President here. Vice
						President message here Vice President here Vice President here.
						Vice President message here Vice President here Vice President
						here. Vice President message here Vice President here Vice
						President here. Vice President message here Vice President here
						Vice President here. Vice President message here Vice President
						here Vice President here. Vice President message here Vice
						President here Vice President here. Vice President message here
						Vice President here Vice President here. Vice President message
						here Vice President here Vice President here.</div>
					<div class="col-sm-12 text-right">
						<h3>
							<strong></strong>
						</h3>
						<div class="m-t-xs font-bold">Vice-President for Academics,
							UNO-Recoletos</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="col-sm-5">
						<div class="text-center">
							<img alt="image" class="img-circle m-t-xs img-responsive"
								src="static/inspinia/img/a41.jpg">
						</div>
					</div>
					<div class="social-body text-justify">Dean message here dean
						message here dean message here. Dean message here dean message
						here dean message here. Dean message here dean message here dean
						message here. Dean message here dean message here dean message
						here. Dean message here dean message here dean message here. Dean
						message here dean message here dean message here. Dean message
						here dean message here dean message here. Dean message here dean
						message here dean message here. Dean message here dean message
						here dean message here. Dean message here dean message here dean
						message here. Dean message here dean message here dean message
						here.</div>
					<div class="col-sm-12 text-right ">
						<h3>
							<strong>Dr. Annabee Magbanua - Claur Ph D</strong>
						</h3>
						<div class="m-t-xs font-bold">Dean College of Arts &
							Sciences, UNO-Recoletos</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<section id="aboutUs" class="container features">
	<jsp:include page="aboutUs.jsp"></jsp:include>
</section>

<section id="gallery" class="gray-section">
	<jsp:include page="gallery.jsp"></jsp:include>
</section>

<section id="events" class="container">
	<jsp:include page="events.jsp"></jsp:include>
</section>

<section id="sme" class="container">
	<jsp:include page="sme.jsp"></jsp:include>
</section>

<section id="farms" class="container">
	<jsp:include page="farms.jsp"></jsp:include>
</section>

<section id="timeline" class="gray-section features">
	<jsp:include page="timeline.jsp"></jsp:include>
</section>

<section id="register" class="container">
	<jsp:include page="register.jsp"></jsp:include>
</section>

<section id="contactUs" class="gray-section contact">
	<jsp:include page="contactUs.jsp"></jsp:include>
</section>

<script>
    $(document).ready(function () {
        $('body').scrollspy({
            target: '.navbar-fixed-top',
            offset: 80
        });

        // Page scrolling feature
        $('a.page-scroll').bind('click', function(event) {
            var link = $(this);
            $('html, body').stop().animate({
                scrollTop: $(link.attr('href')).offset().top - 50
            }, 500);
            event.preventDefault();
            $("#navbar").collapse('hide');
        });
        registerBack();
    });

    var cbpAnimatedHeader = (function() {
        var docElem = document.documentElement,
        header = document.querySelector( '.navbar-default' ),
        didScroll = false,
        changeHeaderOn = 200;
        function init() {
            window.addEventListener( 'scroll', function( event ) {
                if( !didScroll ) {
                    didScroll = true;
                    setTimeout( scrollPage, 250 );
                }
            }, false );
        }
        function scrollPage() {
            var sy = scrollY();
            if ( sy >= changeHeaderOn ) {
                $(header).addClass('navbar-scroll')
            }
            else {
                $(header).removeClass('navbar-scroll')
            }
            didScroll = false;
        }
        function scrollY() {
            return window.pageYOffset || docElem.scrollTop;
        }
        init();

    })();

    // Activate WOW.js plugin for animation on scrol
    new WOW().init();
</script>

<script>
$(document).on('click', '#nav-button', function(event) { 
    $("#close").click(); 
});
</script>