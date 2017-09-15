<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>

<html>
<head>
  <title>Login Page</title>
  <style>
    .error {
      padding: 15px;
      margin-bottom: 20px;
      border: 1px solid transparent;
      border-radius: 4px;
      color: #a94442;
      background-color: #f2dede;
      border-color: #ebccd1;
    }

    .msg {
      padding: 15px;
      margin-bottom: 20px;
      border: 1px solid transparent;
      border-radius: 4px;
      color: #31708f;
      background-color: #d9edf7;
      border-color: #bce8f1;
    }

    #login-box {
      width: 300px;
      padding: 20px;
      margin: 100px auto;
      background: #fff;
      -webkit-border-radius: 2px;
      -moz-border-radius: 2px;
      border: 1px solid #000;
    }
  </style>
</head>
<video poster="http://www.worldatlas.com/r/w728-h425-c728x425/upload/94/3a/11/shutterstock-394537039.jpg" id="bgvid" playsinline autoplay muted loop>
  <source src="https://assets.blackrock.com/blk-corp-assets/cache-1468628316000/images/media-bin/web/corporate/corporate-one/banners/origin-hp-cinemagraph.mp4" type="video/mp4">
</video>
<style>
  body {
    margin: 0;
    background: #000;
  }
  video {
    position: fixed;
    top: 50%;
    left: 50%;
    min-width: 100%;
    min-height: 100%;
    width: auto;
    height: auto;
    z-index: -100;
    transform: translateX(-50%) translateY(-50%);
    background: url('//demosthenes.info/assets/images/polina.jpg') no-repeat;
    background-size: cover;
    transition: 1s opacity;
  }
  .stopfade {
    opacity: .5;
  }

  #fintech {
    font-family: Agenda-Light, Agenda Light, Agenda, Arial Narrow, sans-serif;
    font-weight:100;
    background: rgba(0,0,0,0.3);
    color: white;
    padding: 2rem;
    width: 33%;
    margin:2rem;
    float: right;
    font-size: 1.2rem;
  }
  h1 {
    font-size: 3rem;
    text-transform: uppercase;
    margin-top: 0;
    letter-spacing: .3rem;
  }
  #fintech button {
    display: block;
    width: 80%;
    padding: .4rem;
    border: none;
    margin: 1rem auto;
    font-size: 1.3rem;
    background: rgba(255,255,255,0.23);
    color: #fff;
    border-radius: 3px;
    cursor: pointer;
    transition: .3s background;
  }
  #fintech button:hover {
    background: rgba(0,0,0,0.5);
  }

  a {
    display: inline-block;
    color: #fff;
    text-decoration: none;
    background:rgba(0,0,0,0.5);
    padding: .5rem;
    transition: .6s background;
  }
  a:hover{
    background:rgba(0,0,0,0.9);
  }
  @media screen and (max-width: 500px) {
    div{width:70%;}
  }
  @media screen and (max-device-width: 800px) {
    html { background: url(https://thenewcode.com/assets/images/polina.jpg) #000 no-repeat center center fixed; }
    #bgvid { display: none; }
  }
</style>


<div id="fintech">
  <h1>yafintech-ws</h1>
  <p>create by me 2017
  <p><a href="http://youtube.com">link on some staff</a>
  <h3>Login with Username and Password</h3>

  <c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>
  <c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
  </c:if>

  <form name='loginForm'
        action="<c:url value='/login' />" method='POST'>

    <table>
      <tr>
        <input type="text" class="form-control" id="Username" name="username" placeholder="Please, enter your username" />
      </tr>
      <br>
      <tr>
        <input type="password" class="form-control" id="password" name="password" placeholder="Please, enter your password" />
      </tr>
      <tr>
        <td colspan='2'><input name="submit" type="submit"
                               value="submit"/></td>
      </tr>
    </table>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

  </form>
  <p>The project dedicated to Spring framework technologies ( in general )</p>
  <p>So enjoy ;) </p>
  <button>Pause</button>
</div>



<script>
    var vid = document.getElementById("bgvid");
    var pauseButton = document.querySelector("#fintech button");

    if (window.matchMedia('(prefers-reduced-motion)').matches) {
        vid.removeAttribute("autoplay");
        vid.pause();
        pauseButton.innerHTML = "Paused";
    }

    function vidFade() {
        vid.classList.add("stopfade");
    }

    vid.addEventListener('ended', function()
    {
// only functional if "loop" is removed
        vid.pause();
// to capture IE10
        vidFade();
    });


    pauseButton.addEventListener("click", function() {
        vid.classList.toggle("stopfade");
        if (vid.paused) {
            vid.play();
            pauseButton.innerHTML = "Pause";
        } else {
            vid.pause();
            pauseButton.innerHTML = "Paused";
        }
    })
</script>

</html>