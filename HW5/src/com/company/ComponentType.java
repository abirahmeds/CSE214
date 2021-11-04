package com.company;
/**
 * This is an Enum used to set the type of each component in the FXComponentTree
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #5
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public enum ComponentType {
        Button("Button"), Label("Label"), TextArea("TextArea"), HBox("HBox"),
        VBox("VBox"), AnchorPane("AnchorPane");

        /**
         * This is a constructor used to set the type of the component
         *
         * @param type
         *      the type of component
         */
        String type;
        ComponentType(String type){
            this.type = type;
        }

        /**
        * This is the toString method for Enum
        *
        * @return toString
        *      returns the printable representation of this FXTreeNode's
         *      component type
        */
        public String toString(){
            return type;
        }

        /**
        * This is the boolean method for Enum
        *
        * @return boolean
        *      returns whether the userResp of the component type they chose for
        *      the node is a valid Enum or not
        */
        static public boolean isValid(String userResp) {
            ComponentType[] types = ComponentType.values();
            for (ComponentType type : types)
             if (type.toString().equalsIgnoreCase(userResp))
                    return true;
            return false;
        }

        /**
         * This is a method to set the type for the FXTreeNode
         *
         * @return boolean
         *      returns whether the userResp of the component type they chose
         *      for the node is a valid Enum or not
         */
        public static String setType(String userResp) {
            ComponentType[] types = ComponentType.values();
            for (ComponentType type : types)
                if (type.toString().equalsIgnoreCase(userResp))
                    return type.toString();
            return null;
        }

        /**
        * This is the boolean method for Enum
        *
        * @return boolean
        *      returns whether the type of component is a container or not, a
         *      container includes being an AnchorPane, HBox, and VBox.
        */
        static boolean isContainer(String type){
        if (type.equalsIgnoreCase(HBox.toString()) ||
                type.equalsIgnoreCase(AnchorPane.toString()) ||
                type.equalsIgnoreCase(VBox.toString()))
            return true;
        return false;
    }
}
