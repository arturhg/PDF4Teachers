/*
 * Copyright (c) 2021. Clément Grennerat
 * All rights reserved. You must refer to the licence Apache 2.
 */

package fr.clementgre.pdf4teachers.utils.svg;

import fr.clementgre.pdf4teachers.utils.exceptions.PathParseException;
import org.apache.batik.parser.AWTPathProducer;
import org.apache.batik.parser.ParseException;
import org.apache.batik.parser.PathParser;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Collections;
import java.util.List;

public class SVGUtils{
    
    public static final List<String> ACCEPTED_EXTENSIONS = Collections.singletonList("svg");
    
    public static String transformPath(String path, float xFactor, float yFactor, float translateX, float translateY,
                                       boolean invertX, boolean invertY, float currentWidth, float currentHeight, int decimals) throws PathParseException{
        
        PathParser parser = new PathParser();
        SVGScalerHandler handler = new SVGScalerHandler(xFactor, yFactor, translateX, translateY, invertX, invertY, currentWidth, currentHeight, decimals);
        parser.setPathHandler(handler);
        try{
            parser.parse(path);
        }catch(ParseException e){
            throw new PathParseException(e);
        }
        
        
        return handler.getTransformedPath();
    }
    
    public static String addArrowsToPath(String path, float arrowSize, int decimals) throws PathParseException{
        
        PathParser parser = new PathParser();
        SVGArrowsCreatorHandler handler = new SVGArrowsCreatorHandler(decimals, arrowSize, 30);
        parser.setPathHandler(handler);
        try{
            parser.parse(path);
        }catch(ParseException e){
            throw new PathParseException(e);
        }
        
        return handler.getTransformedPath();
    }
    
    public static String rotatePath(String path, float degAngle, int decimals) throws PathParseException{
        PathParser parser = new PathParser();
        SVGRotateHandler handler = new SVGRotateHandler(degAngle, decimals);
        parser.setPathHandler(handler);
        try{
            parser.parse(path);
        }catch(ParseException e){
            throw new PathParseException(e);
        }
    
        return handler.getTransformedPath();
    }
    
    public static Shape convertToAwtShape(String path) throws PathParseException{
        PathParser parser = new PathParser();
        AWTPathProducer producer = new AWTPathProducer();
    
        producer.setWindingRule(Path2D.WIND_NON_ZERO);
        parser.setPathHandler(producer);
        try{
            parser.parse(path);
        }catch(ParseException e){
            throw new PathParseException(e);
        }
    
        return producer.getShape();
    }
    
}
