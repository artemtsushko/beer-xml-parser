<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head><meta charset = "UTF-8"/></head>
            <body>
                <h2>Beer Collection</h2>
                <table border="1">
                    <xsl:for-each select="BeerCollection/beer">
                        <tr bgcolor="#EEAD0E">
                            <th>Назва</th>
                            <td><xsl:value-of select="name"/></td>
                        </tr>
                        <tr>
                            <th>Тип</th>
                            <td><xsl:value-of select="type"/></td>
                        </tr>
                        <tr>
                            <th>Алкогольне</th>
                            <xsl:choose>
                                <xsl:when test="al = 'true'">
                                    <td>так</td>
                                </xsl:when>
                                <xsl:otherwise>
                                    <td>ні</td>
                                </xsl:otherwise>
                            </xsl:choose>
                        </tr>
                        <tr>
                            <th>Виробник</th>
                            <td><xsl:value-of select="manufacturer"/></td>
                        </tr>
                        <tr>
                            <th>Склад</th>
                            <td>
                                <xsl:for-each select="ingredients">
                                    <xsl:value-of select="."/><br/>
                                </xsl:for-each>
                            </td>
                        </tr>
                        <tr><th>Характеристики</th><td></td></tr>
                        <xsl:apply-templates select="chars"/>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="chars">
        <xsl:apply-templates select="strength"/>
        <tr>
            <td>Прозорість</td>
            <td><xsl:value-of select="transparency"/>%</td>
        </tr>
        <tr>
            <td>Фільтроване</td>
            <xsl:choose>
                <xsl:when test="filtered = 'true'">
                    <td>так</td>
                </xsl:when>
                <xsl:otherwise>
                    <td>ні</td>
                </xsl:otherwise>
            </xsl:choose>
        </tr>
        <tr>
            <td>Енергетична цінність</td>
            <td><xsl:value-of select="energy"/> ккал</td>
        </tr>
        <tr>
            <td>Упаковка</td>
            <td>
                <xsl:for-each select="packaging">
                    <xsl:value-of select="volume"/>л 
                    <xsl:choose>
                        <xsl:when test="material = 'glass'">
                            скляна
                        </xsl:when>
                        <xsl:when test="material = 'PET'">
                            ПЕТ
                        </xsl:when>
                        <xsl:when test="material = 'alu'">
                            алюмінієва
                        </xsl:when>
                    </xsl:choose>
                    <br/>
                </xsl:for-each>
            </td>
        </tr>
    </xsl:template>
    <xsl:template match="strength">
        <tr>
            <td>Міцність</td>
            <td><xsl:value-of select="."/> об.</td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
