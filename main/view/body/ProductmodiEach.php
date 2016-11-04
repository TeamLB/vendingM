<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:21
 */
$list = isset($_SESSION['info_list']) ? $_SESSION['info_list'] : null;
?>


<body>
<form action="../controller/mainCtrl.php?func=110" method="post">

    <img src='<?php echo $list[0]['Pimage']?>'>
    제품명&nbsp:&nbsp<input type='text' name = 'Pname' value='<?php echo $list[0]['Pname']?>'>
    제품가&nbsp:&nbsp<input type='text' name = 'Pprice' value='<?php echo $list[0]['Pprice']?>'>
    재고량&nbsp:&nbsp<input type='text' name = 'Pstock' value='<?php echo $list[0]['Pstock']?>'>
    <input type='hidden' name='target' value='<?php echo $list[0]['Pname']?>'>
    <br>
    <a href="../controller/mainCtrl.php?func=100">전체 항목</a>
    <br>
    <input type="submit" value="수정">
</form>
</body>
