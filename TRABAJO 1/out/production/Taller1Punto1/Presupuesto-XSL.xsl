<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : Presupuesto-XSL.xsl
    Created on : 29 de septiembre de 2020, 6:21
    Author     : vcjladino
    Description:
        Purpose of transformation follows.
-->
    -->
              <body>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
     <xsl:output method="html"/>
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="presepuesto">
        <html>
            <head>
                 <title>Presupuesto-XSL.xsl</title>
            </head>
            <style>
                #customers {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 70%;
                }

                #customers td, #customers th {
                border: 1px solid #ddd;
                padding: 8px;
                }

                #customers tr:nth-child(even){background-color: #f2f2f2;}

                #customers tr:hover {background-color: #ddd;}

                #customers th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #4CAF50;
                color: white;
                }
                .negrilla {
                font-weight: bold;                
                }
                .verde {
                background-color: #e4ffe5;
                }
                .rojo {
                background-color: #ffeded;
                }
            </style>
            <body>
                <h1 style="color: #196844;text-align: center;fonst-size:2.5em;">
                    Presupuesto   Poli 
                </h1>
                <table border="1" align="center" id="customers">
                    <tr style="padding: 5px">
                        <th style="padding: 8px">Tipo</th>
                        <th>Concepto</th>
                        <th>2019</th>
                        <th>2020</th>
                        <th>2021</th>
                        <th>2022</th>
                        <th>2023</th>
                        <th>Total</th>
                        <th>Cantidad letras</th>
                    </tr>                    
                    <xsl:for-each select="Ingresos/Ingreso" >
                        <tr>                
                            <td class="negrilla verde">
                                <xsl:value-of select="name()" />
                            </td>
                            <td>
                                <xsl:number value="position()" format="1. " />
                                <xsl:value-of select="@type" />
                            </td>
                            <xsl:for-each select="li" >
                                <td> 
                                    <xsl:value-of select="." /> 
                                </td>
                            </xsl:for-each>      
                            <xsl:variable name="nodes" select="li" />      
                            <td class="negrilla"> 
                                <xsl:value-of select="sum(li/@value)" /> 
                            </td>
                        </tr>
                    </xsl:for-each>
                    <xsl:for-each select="Egresos/Egreso" >
                        <tr>                
                            <td class="negrilla rojo">
                                <xsl:value-of select="name()" />
                            </td>
                            <td>
                                <xsl:number value="position()" format="1. " />
                                <xsl:value-of select="@type" />
                            </td>
                            <xsl:for-each select="li" >
                                <td> 
                                    <xsl:value-of select="." /> 
                                </td>
                            </xsl:for-each>                             
                            <td class="negrilla"> 
                                <xsl:value-of select="sum(li/@value)" /> 
                            </td> 
                            <td class="negrilla">                            
                                <xsl:value-of select="string-length(@type)"/>
                            </td>            
                        </tr>
                    </xsl:for-each> 
                    <tr>
                        <td class="negrilla">Totales</td>
                        <td></td>                        
                        <td class="negrilla">                          
                            <xsl:value-of select="sum(//li[@col='1']/@value)" /> 
                        </td>  
                        <td class="negrilla">                          
                            <xsl:value-of select="sum(//li[@col='2']/@value)" /> 
                        </td>  
                        <td class="negrilla">                          
                            <xsl:value-of select="sum(//li[@col='3']/@value)" /> 
                        </td>  
                        <td class="negrilla">                          
                            <xsl:value-of select="sum(//li[@col='4']/@value)" /> 
                        </td>  
                        <td class="negrilla">                          
                            <xsl:value-of select="sum(//li[@col='5']/@value)" /> 
                        </td>                                             
                    </tr>                                                                             
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

