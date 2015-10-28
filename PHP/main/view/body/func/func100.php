<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-10-25
 * Time: ���� 9:45
 */

?>

<html>
<head>
    <title>Login 해양!</title>
    <style>
        form {
            text-align: center;
        }
    </style>
</head>

<body>
<h2 align="center">로...그...인</h2>

<form action="../../../ctrl/main_CTL.php" method="post">
    <img src = "http://i.imgur.com/7pDE27y.jpg"><br><br>
    ID<input type = "text" name = "id" required>&nbsp;&nbsp;&nbsp;
    PD<input type = "text" name = "passwd" required>
    <input type = "hidden" name = func value = "110">
    <input type = "submit" name = "login" value = "LOGIN">
</form>
</body>
</html>
