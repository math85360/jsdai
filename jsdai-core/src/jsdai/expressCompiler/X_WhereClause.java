/*
 * $Id$
 *
 * JSDAI(TM), a way to implement STEP, ISO 10303
 * Copyright (C) 1997-2008, LKSoftWare GmbH, Germany
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License
 * version 3 as published by the Free Software Foundation (AGPL v3).
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * JSDAI is a registered trademark of LKSoftWare GmbH, Germany
 * This software is also available under commercial licenses.
 * See also http://www.jsdai.net/
 */

// %modified: 1016210358096 %

/* Generated By:JJTree: Do not edit this line. X_WhereClause.java */
package jsdai.expressCompiler;

import java.util.*;


public class X_WhereClause
  extends SimpleNode {
  Vector domain_rules;
  jsdai.SExtended_dictionary_schema.AWhere_rule where_rules;

  public X_WhereClause(int id) {
    super(id);
    domain_rules = new Vector();
  }

  public X_WhereClause(Compiler2 p, int id) {
    super(p, id);
    domain_rules = new Vector();
  }

  /** Accept the visitor. **/
  public Object jjtAccept(Compiler2Visitor visitor, Object data)
                   throws jsdai.lang.SdaiException {
    return visitor.visit(this, data);
  }
}