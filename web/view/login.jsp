<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="./css/login.css"/>
</head>
<body>
    <form action="login" method="POST">
    <div class="cont">
        <input type="hidden" name="action" value="loginSuccessfully">
                <div class="form sign-in">
                    <h2>Đăng nhập</h2>
                    <label>
                        <span>Email</span>
                        <input type="email" name="user"/>
                    </label>
                    <label>
                        <span>Mật khẩu</span>
                        <input type="password" name="pass"/>
                    </label>
                    <p class="forgot-pass">Forgot password?</p>
                    <button type="submit" class="submit" style="background: #00AFEF; color: white;">Đăng nhập</button>

                </div>
                <div class="sub-cont">
                    <div class="img">
                        <div class="img__text m--up">

                            <h3>Chưa có tài khoản? Đăng kí tại đây!<h3>
                                    </div>
                                    <div class="img__text m--in">

                                        <h3>Nếu đã có tài khoản, hãy đăng nhập<h3>
                                                </div>
                                                <div class="img__btn">
                                                    <span class="m--up">Đăng kí</span>
                                                    <span class="m--in">Đăng nhập</span>
                                                </div>
                                                </div>
                                                <div class="form sign-up">
                                                    <h2>Tạo tài khoản của bạn</h2>
                                                    <label>
                                                        <span>Số CMND</span>
                                                        <input type="text" />
                                                    </label>
                                                    <label>
                                                        <span>Họ và Tên</span>
                                                        <input type="text" />
                                                    </label>
                                                    <label>
                                                        <span>Email</span>
                                                        <input type="email" />
                                                    </label>
                                                    <label>
                                                        <span>Mật khẩu</span>
                                                        <input type="password" />
                                                    </label>
                                                    <button type="submit" class="submit" style="background: #00AFEF; color: white;">Đăng kí</button>


                    </div>
                </div>
            </div>
    </form>

                                                <script>
                                                    document.querySelector('.img__btn').addEventListener('click', function () {
                                                        document.querySelector('.cont').classList.toggle('s--signup');
                                                    });
                                                </script>
</body>
</html>