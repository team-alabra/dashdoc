namespace Dashdoc.API.Infrastructure.Utils;

// TODO - fix styling and add templates to DB
// class is no longer needed once EmailTemplateRepository is created.
public static class EmailContentBuilder
{
    public static string Build(string name)
    {
      var messageContent = $@"
      <html>
        <head>
          <meta charset=""utf-8"" />
          <style>
            #container {{
              margin: auto;
              height: fit-content;
              align-items: center;
            }}
            .email-header th {{
              font-size: 40px;
              padding-bottom: 2rem;
            }}
            .email-content-container {{
              height: 100%;
              border: 1px solid lightgrey;
              width: 300px;
              height: 500px;
              padding: 1rem;
              vertical-align: top;
            }}
            #email-footer th {{
              padding-top: 3rem;
              text-align: center;
            }}

          </style>
        </head>
        <body>
          <table id=""container"">
            <thead class=""email-header"">
              <th>Dashdoc</th>
            </thead>
            <tbody>
              <tr>
                <td class=""email-content-container"">
                  <p>Hey {name},</p>
                  <p>Your verification code is: 12345</p>
                </td>
              </tr>
            </tbody>
            <tfoot id=""email-footer"">
              <th>2024 Dashdoc LLC. All rights reserved</th>
            </tfoot>
          </table>
        </body>
      </html>
      ";

      return messageContent;
    }
}