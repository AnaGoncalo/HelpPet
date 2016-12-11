<%-- 
    Document   : menu
    Created on : 02/06/2016, 15:02:07
    Author     : anne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid ">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a style="color: #FF7F00;" class="navbar-brand" href="index.jsp">Help Pet</a>
        </div>

        <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <h:form>
                    <h:commandButton action="listarAnimais.xhtml" value="Animais"/>
                    <h:commandButton action="listarDenuncias.xhtml" value="Denuncias"/>
                    <h:commandButton action="listarExperiencias.xhtml" value="Experiencias"/>
                    <h:commandButton action="listarEstoques.xhtml" value="Estoques"/>
                    <h:commandButton action="loginSignin.xhtml" value="Login/Signin"/>
                </h:form>
            </ul>
        </div>
    </div>
</nav>