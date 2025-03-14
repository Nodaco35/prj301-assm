<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="./css/login.css"/>
    </head>
    <body>
        <div class="cont <%= "register".equals(request.getParameter("optionLoginOrRegister")) || "register".equals(request.getAttribute("optionLoginOrRegister")) ? "s--signup" : "" %>">
            <div class="form sign-in">
                <form action="login" method="POST">
                    <input type="hidden" name="action" value="loginSuccessfully">
                    <h2 style="margin-top: 50px">Đăng nhập</h2>
                    <label>
                        <span>Email</span>
                        <input type="email" name="user" required/>
                    </label>
                    <label>
                        <span>Mật khẩu</span>
                        <input type="password" name="pass" required/>
                    </label>
                    <p class="forgot-pass">Forgot password?</p>
                    <label><h3 style="color: red;">${requestScope.error}</h3></label>
                    <button type="submit" class="submit" style="background: #00AFEF; color: white;">Đăng nhập</button>
                </form>
            </div>
            <div class="sub-cont">
                <form action="login" method="POST">
                    <input type="hidden" name="action" value="registerSuccessfully">
                    <div class="img">
                        <div class="img__text m--up">
                            <h3>Chưa có tài khoản? Đăng kí tại đây!</h3>
                        </div>
                        <div class="img__text m--in">
                            <h3>Nếu đã có tài khoản, hãy đăng nhập</h3>
                        </div>
                        <div class="img__btn">
                            <span class="m--up">Đăng kí</span>
                            <span class="m--in">Đăng nhập</span>
                        </div>
                    </div>
                    <h2 style="margin-top: 20px">Tạo tài khoản của bạn</h2>
                    <label>
                        <span>Số CMND</span>
                        <input type="text" name="idCard" required/>
                    </label>
                    <label>
                        <span>Họ và Tên</span>
                        <input type="text" name="fullName" required/>
                    </label>
                    <label>
                        <span>Email</span>
                        <input type="email" name="email" required/>
                    </label>
                     <label>
                        <span>Số điện thoại</span>
                        <input type="text" name="phone" pattern="\d{10}" title="Số điện thoại phải gồm 10 chữ số" required/>
                    </label>
                    <label>
                        <span>Mật khẩu</span>
                        <input type="password" name="password" required/>
                    </label>
                    <label>
                        <span>Địa chỉ</span>
                        <input type="text" name="address" required/>
                    </label>
                    <label><h3 style="color: red;">${requestScope.errorR}</h3></label>
                    <label><h3 style="color: green;">${requestScope.mes}</h3></label>
                    <button type="submit" class="submit" style="background: #00AFEF; color: white;">Đăng kí</button>
                </form>
            </div>
        </div>
        <script>
            document.querySelector('.img__btn').addEventListener('click', function () {
                document.querySelector('.cont').classList.toggle('s--signup');
            });
        </script>
    </body>
</html>
