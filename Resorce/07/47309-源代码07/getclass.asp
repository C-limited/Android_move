<%
    dim data(4)
    data(0)="[{""no"":1,""name"":""语文""},{""no"":2,""name"":""语文""},{""no"":3,""name"":""数学""},{""no"":5,""name"":""物理""},{""no"":6,""name"":""物理""}]"
    data(1)="[{""no"":1,""name"":""英语""},{""no"":2,""name"":""英语""},{""no"":3,""name"":""化学""},{""no"":5,""name"":""数学""},{""no"":6,""name"":""生物""}]"
    data(2)="[{""no"":1,""name"":""语文""},{""no"":2,""name"":""语文""},{""no"":4,""name"":""历史""},{""no"":5,""name"":""化学""},{""no"":6,""name"":""地理""}]"
    data(3)="[{""no"":1,""name"":""英语""},{""no"":2,""name"":""英语""},{""no"":3,""name"":""化学""},{""no"":5,""name"":""数学""},{""no"":6,""name"":""生物""}]"
    data(4)="[{""no"":1,""name"":""语文""},{""no"":2,""name"":""语文""},{""no"":4,""name"":""数学""},{""no"":5,""name"":""物理""},{""no"":6,""name"":""物理""}]"
    if Request.QueryString<>"" then
        index=Request("index")+0
        if index>=0 and index<=4 then
            response.write(data(index))
        end if
    end if	
%>	