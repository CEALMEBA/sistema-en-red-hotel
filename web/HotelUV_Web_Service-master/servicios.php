<?php


$host = "localhost";
$user = "id13826075_uv";
$password = "GQHtOIpQt*Ld6]>%";
$database = "id13826075_hotel";

$conn = mysqli_connect($host, $user, $password, $database);
$hab = "0";
if(isset($_GET['ajuste'])){
  $ajustepeticion = "UPDATE Peticiones SET estado=0,pluces=null,ptemperatura=null,pllaves=null WHERE habitacion = '$_GET[ajuste]' ";
  $sql_statement = $conn->prepare($ajustepeticion);
  $sql_statement->execute();
}else{
  if(isset($_GET['hab'])){
    $hab = $_GET['hab'];
  }else{
    header('Location:index.php');
  }

  if(isset($_GET['luces'])){

    $cambiarluces = "UPDATE Servicios SET luces='$_GET[luces]' WHERE habitacion = '$hab' ";
    $sql_statement = $conn->prepare($cambiarluces);
    $sql_statement->execute();

  }else if(isset($_GET['temperatura'])){

    $cambiartemp = "UPDATE Servicios SET temperatura='$_GET[temperatura]' WHERE habitacion = '$hab' ";
    $sql_statement = $conn->prepare($cambiartemp);
    $sql_statement->execute();

  }else if(isset($_GET['llaves'])){

    $cambiarllaves = "UPDATE Servicios SET llaves='$_GET[llaves]' WHERE habitacion = '$hab' ";
    $sql_statement = $conn->prepare($cambiarllaves);
    $sql_statement->execute();

  }

}



?>
