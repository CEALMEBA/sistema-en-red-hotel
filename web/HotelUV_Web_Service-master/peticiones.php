<?php

$host = "localhost";
$user = "id13826075_uv";
$password = "GQHtOIpQt*Ld6]>%";
$database = "id13826075_hotel";

$conn = mysqli_connect($host, $user, $password, $database);

if(isset($_POST['hab'])){
  $query = "SELECT * FROM Peticiones WHERE habitacion = '$_POST[hab]'";
  $sql_statement = $conn->prepare($query);
  $sql_statement->execute();
  $result = $sql_statement->get_result();
  if ($result->num_rows > 0) {

    if(isset($_POST['luces'])){

      $cambiarluces = "UPDATE Peticiones SET pluces='$_POST[luces]',estado=1 WHERE habitacion = '$_POST[hab]' ";
      $sql_statement = $conn->prepare($cambiarluces);
      $sql_statement->execute();

    }else if(isset($_POST['temperatura'])){

      $cambiartemp = "UPDATE Peticiones SET ptemperatura='$_POST[temperatura]',estado=1 WHERE habitacion = '$_POST[hab]' ";
      $sql_statement = $conn->prepare($cambiartemp);
      $sql_statement->execute();

    }else if(isset($_POST['llaves'])){

      $cambiarllaves = "UPDATE Peticiones SET pllaves='$_POST[llaves]',estado=1 WHERE habitacion = '$_POST[hab]' ";
      $sql_statement = $conn->prepare($cambiarllaves);
      $sql_statement->execute();

    }

  }else{
    if(isset($_POST['luces'])){

      $query = "INSERT INTO Peticiones (habitacion,estado,pluces,ptemperatura,pllaves) VALUES ('$_POST[hab]',1,'$_POST[luces]','0',0)";
      $sql_statement = $conn->prepare($query);
      $sql_statement->execute();

    }else if(isset($_POST['temperatura'])){

      $query = "INSERT INTO Peticiones (habitacion,estado,pluces,ptemperatura,pllaves) VALUES ('$_POST[hab]',1,0,'$_POST[temperatura]',0)";
      $sql_statement = $conn->prepare($query);
      $sql_statement->execute();

    }else if(isset($_POST['llaves'])){

      $query = "INSERT INTO Peticiones (habitacion,estado,pluces,ptemperatura,pllaves) VALUES ('$_POST[hab]',1,0,'0','$_POST[llaves]')";
      $sql_statement = $conn->prepare($query);
      $sql_statement->execute();

    }
  }
}else{
  header('Location:index.php');
}

?>
