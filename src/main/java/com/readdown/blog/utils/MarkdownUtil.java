package com.readdown.blog.utils;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

/**
 * @author feng
 * @Date 2017/11/5
 * @Time 19:45
 */
public class MarkdownUtil {


    /**
     * markdown 格式转换成Html
     * @param markdown
     * @return
     */
    public static String markdownToHtml(String markdown){
        Parser parser =Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }


    public static String markdownToHtmlExtensions(String markdown){

//        h标题生成id
        Set<Extension> headingAnchorExtension = Collections.singleton(HeadingAnchorExtension.create());
//        转换table的Html
         List<Extension>  tableExtension = Arrays.asList(TablesExtension.create());

        Parser parser = Parser.builder()
                .extensions(tableExtension)
                .build();

        Node document = parser.parse(markdown);

        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtension)
                .extensions(tableExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                        return new CustomAttributeProvider();
                    }
                }).build();

        return renderer.render(document);
    }

    static class CustomAttributeProvider implements AttributeProvider{
        @Override
        public void setAttributes(Node node, String tagName ,Map<String, String> attributes) {
            if (node instanceof Link){
                attributes.put("target","_blank");
            }

            if (node instanceof TableBlock){
                attributes.put("class","ui called table");
            }
        }
    }
}
