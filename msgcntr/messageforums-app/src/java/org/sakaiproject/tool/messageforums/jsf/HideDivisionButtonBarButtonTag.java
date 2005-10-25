/**********************************************************************************
 * $URL: $
 * $Id: $
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004 The Regents of the University of Michigan, Trustees of Indiana University,
 *                  Board of Trustees of the Leland Stanford, Jr., University, and The MIT Corporation
 *
 * Licensed under the Educational Community License Version 1.0 (the "License");
 * By obtaining, using and/or copying this Original Work, you agree that you have read,
 * understand, and will comply with the terms and conditions of the Educational Community License.
 * You may obtain a copy of the License at:
 *
 *      http://cvs.sakaiproject.org/licenses/license_1_0.html
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 **********************************************************************************/

package org.sakaiproject.tool.messageforums.jsf;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentTag;

/**
 * Modified version of HideDivision that will allow links or
 * "buttons" in the H4 tag.
 */

public class HideDivisionButtonBarButtonTag extends UIComponentTag {
    private String action;

    private String value;

    private String id;

    private String first;

    private String rows;

    private String var;

    private String separator;

    private String onclick;

    private String ondblclick;

    private String onkeydown;

    private String onkeypress;

    private String onkeyup;

    private String onmousedown;

    private String onmousemove;

    private String onmouseout;

    private String onmouseover;

    private String onmouseup;

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getComponentType() {
        return ("javax.faces.Output");
    }

    public String getRendererType() {
        return "org.sakaiproject.HideDivisionButtonBarButton";
    }

    /**
     * 
     * @param first
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * The rows property refers to rows in the mini-model, NOT table rows.
     * 
     * @param rows
     */
    public void setRows(String rows) {
        this.rows = rows;
    }

    /**
     * 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 
     * @param var
     */
    public void setVar(String var) {
        this.var = var;
    }

    /**
     * 
     * @param separator
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }

    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }

    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }

    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }

    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }

    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }

    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }

    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }

    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }

    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        TagUtil.setString(component, "action", action);
        TagUtil.setString(component, "value", value);
        TagUtil.setString(component, "ondblclick", ondblclick);
        TagUtil.setString(component, "onclick", onclick);
        TagUtil.setString(component, "onkeydown", onkeydown);
        TagUtil.setString(component, "onkeypress", onkeypress);
        TagUtil.setString(component, "onkeyup", onkeyup);
        TagUtil.setString(component, "onmousedown", onmousedown);
        TagUtil.setString(component, "onmousemove", onmousemove);
        TagUtil.setString(component, "onmouseout", onmouseout);
        TagUtil.setString(component, "onmouseover", onmouseover);
        TagUtil.setString(component, "onmouseup", onmouseup);
    }

}
