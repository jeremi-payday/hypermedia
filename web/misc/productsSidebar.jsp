<%@ page import="misc.SessionBean"%>
<div id="sidebar" class="background-white border-light">
    
    <div style="border-bottom: 1px solid black;"><h4>Filter</h4></div>
            
    <div><input type="checkbox"/> <label>some random filter</label><br></div>
    <div><input type="checkbox"/> <label>some random filter</label><br></div>
    <div><input type="checkbox"/> <label>some random filter</label><br></div>
    <div><input type="checkbox"/> <label>some random filter</label><br></div>
    
    <div>
        <label style="text-align: center"> Current costumers <br>
            <%= SessionBean.getNB_SESSIONS()%> 
        </label>
    </div>

</div>