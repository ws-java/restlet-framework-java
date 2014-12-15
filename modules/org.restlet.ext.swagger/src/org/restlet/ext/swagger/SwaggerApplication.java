/**
 * Copyright 2005-2014 Restlet
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or or EPL 1.0 (the "Licenses"). You can
 * select the license that you prefer but you may not use this file except in
 * compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://restlet.com/products/restlet-framework
 * 
 * Restlet is a registered trademark of Restlet S.A.S.
 */

package org.restlet.ext.swagger;

import java.util.HashMap;
import java.util.Map;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.ext.apispark.internal.introspection.DocumentedApplication;
import org.restlet.ext.apispark.internal.model.Section;
import org.restlet.routing.Filter;
import org.restlet.routing.Route;
import org.restlet.routing.Router;

/**
 * Swagger enabled application. This subclass of {@link Application} can
 * describe itself in the format described by the <a
 * href="https://github.com/wordnik/swagger-spec/wiki">Swagger specification
 * project</a>. <br>
 * <br>
 * It requires you to set up a specific end point that serves the resource
 * listing and a sub-resource that serves the API declaration of a specific
 * resource.<br>
 * <br>
 * By default, nothing is required. This application adds to its inbound root
 * the endpoints ("/api-docs", and "/api-docs/{resource}") required by Swagger
 * specification. You can override this behavior by using the
 * SwaggerApplication#attachSwagger* methods<br>
 * 
 * By default, both descriptions are generated by introspecting the application
 * itself. You can override this behavior by specifying your own implementation
 * of {@link SwaggerSpecificationRestlet}.
 * 
 * @author Thierry Boileau
 * @see SwaggerSpecificationRestlet
 * @see Swagger2SpecificationRestlet
 */
public class SwaggerApplication extends Application implements
        DocumentedApplication {

    /**
     * Returns the next router available.
     * 
     * @param current
     *            The current Restlet to inspect.
     * @return The first router available.
     */
    private static Router getNextRouter(Restlet current) {
        Router result = null;
        if (current instanceof Router) {
            result = (Router) current;
        } else if (current instanceof Filter) {
            result = getNextRouter(((Filter) current).getNext());
        }

        return result;
    }

    /**
     * Indicates if the given {@link Restlet} provides a
     * {@link SwaggerSpecificationRestlet} able to generate Swagger
     * documentation.
     * 
     * @param current
     *            The current Restlet to inspect.
     * @return True if the given {@link Restlet} provides a
     *         {@link SwaggerSpecificationRestlet} able to generate Swagger
     *         documentation.
     */
    private static boolean isDocumented(Restlet current) {
        boolean documented = false;

        Router router = null;
        if (current instanceof Router) {
            router = (Router) current;
            for (Route route : router.getRoutes()) {
                if (isDocumented(route.getNext())) {
                    documented = true;
                    break;
                }
            }
        } else if (current instanceof Filter) {
            documented = isDocumented(((Filter) current).getNext());
        } else if (current instanceof SwaggerSpecificationRestlet) {
            documented = true;
        }

        return documented;
    }

    /** Indicates if this application can document herself. */
    private boolean documented;

    /**
     * The Sections of the Web API
     */
    private Map<String, Section> sections;

    /**
     * Defines two routes, one for the high level "Resource listing", and the
     * other one for the "API declaration". The second route is a sub-resource
     * of the first one, defined with the path variable "resource".
     * 
     * @param router
     *            The router on which defining the new route.
     * @param resourceListingPath
     *            The path to which attach the Restlet that serves the resource
     *            listing.
     * @param resourceListingRestlet
     *            The Restlet that serves the resource listing.
     * @param apiDeclarationPath
     *            The path to which attach the Restlet that serves the
     *            declaration of a specific resource.
     * @param apiDeclarationRestlet
     *            The Restlet that serves the declaration of a specific
     *            resource.
     */
    public void attachSwaggerDocumentationRestlets(Router router,
            String resourceListingPath, Restlet resourceListingRestlet,
            String apiDeclarationPath, Restlet apiDeclarationRestlet) {
        router.attach(resourceListingPath, resourceListingRestlet);
        router.attach(apiDeclarationPath, apiDeclarationRestlet);
        documented = true;
    }

    /**
     * Defines two routes, one for the high level "Resource listing" (by default
     * "/api-docs"), and the other one for the "API declaration". The second
     * route is a sub-resource of the first one, defined with the path variable
     * "resource" (ie "/api-docs/{resource}").
     * 
     * @param router
     *            The router on which defining the new route.
     * 
     * @see #attachSwaggerSpecificationRestlet(org.restlet.routing.Router,
     *      String) to attach it with a custom path
     */
    public void attachSwaggerSpecificationRestlet(Router router) {
        getSwaggerSpecificationRestlet(getContext()).attach(router);
        documented = true;
    }

    /**
     * Defines two routes, one for the high level "Resource listing", and the
     * other one for the "API declaration". The second route is a sub-resource
     * of the first one, defined with the path variable "resource".
     * 
     * @param router
     *            The router on which defining the new route.
     * @param path
     *            The root path of the documentation Restlet.
     * 
     * @see #attachSwaggerSpecificationRestlet(org.restlet.routing.Router) to
     *      attach it with the default path
     */
    public void attachSwaggerSpecificationRestlet(Router router, String path) {
        getSwaggerSpecificationRestlet(getContext()).attach(router, path);
        documented = true;
    }

    /**
     * Overrides the parent's implementation. It checks that the application has
     * been documented using a {@link SwaggerSpecificationRestlet}. By default,
     * the documentation is attached to the high level router, with the
     * "/api-docs" path.
     */
    @Override
    public Restlet getInboundRoot() {
        Restlet inboundRoot = super.getInboundRoot();
        if (!documented) {
            synchronized (this) {
                if (!documented) {
                    Router rootRouter = getNextRouter(inboundRoot);

                    // Check that the application has been documented.
                    documented = isDocumented(rootRouter);
                    if (!documented) {
                        attachSwaggerSpecificationRestlet(rootRouter);
                        documented = true;
                    }
                }
            }
        }
        return inboundRoot;
    }

    @Override
    public Map<String, Section> getSections() {
        if (sections == null) {
            sections = new HashMap<String, Section>();
        }
        return sections;
    }

    /**
     * The dedicated {@link Restlet} able to generate the Swagger specification
     * formats.
     * 
     * @return The {@link Restlet} able to generate the Swagger specification
     *         formats.
     */
    public SwaggerSpecificationRestlet getSwaggerSpecificationRestlet(
            Context context) {
        SwaggerSpecificationRestlet result = new SwaggerSpecificationRestlet(
                context);
        result.setApiInboundRoot(this);
        return result;
    }
}
