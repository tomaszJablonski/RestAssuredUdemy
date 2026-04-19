package groovy

import groovy.json.JsonSlurper

class ForTest {
    static void main(String[] args) {

        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(
                """
{
    "workspaces": [
        {
            "id": "945973a6-9da4-4d7a-89cb-2ba6c5a64397",
            "name": "My Workspace3",
            "type": "personal",
            "visibility": "personal",
            "createdBy": "20437595",
            "about": "",
            "createdAt": "2022-04-08T23:17:37.000Z",
            "updatedAt": "2026-04-18T16:23:14.000Z"
        },
        {
            "id": "e413dab2-c9e8-4d96-8d1e-35e01b5d2f97",
            "name": "My Workspace4",
            "type": "personal",
            "visibility": "personal",
            "createdBy": "20437595",
            "about": "",
            "createdAt": "2026-04-18T16:27:38.000Z",
            "updatedAt": "2026-04-18T16:27:38.000Z"
        },
        {
            "id": "d4fd39dd-b684-4a51-a8e8-97cca15ba2ff",
            "name": "My Workspace5",
            "type": "personal",
            "visibility": "personal",
            "createdBy": "20437595",
            "about": "",
            "createdAt": "2026-04-18T16:27:44.000Z",
            "updatedAt": "2026-04-18T16:27:44.000Z"
        }
    ]
}
"""
        )

        def queryAllNameWorkspaces = object.workspaces*.name
        println(queryAllNameWorkspaces)

        def queryFirstNameWorkspaces = object.workspaces[0].name
        println(queryFirstNameWorkspaces)

        def querySize = object.workspaces.size()
        println(querySize)
    }
}