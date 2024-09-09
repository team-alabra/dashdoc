using System.Security.Claims;
using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Domain.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Dashdoc.API.Server.Controllers;

[ApiController]
[Route("/api/test")]
[Authorize]
public class TestController: ControllerBase
{
    private readonly IAgencyRepository _agencyRepository;
    
    public TestController(IAgencyRepository agencyRepository)
    {
        _agencyRepository = agencyRepository;
    }

    // TODO - replace with real Provider routes
    [HttpGet]
    public async Task<ActionResult> GetV1()
    {
        try
        {
            var res = await _agencyRepository.GetByIdAsync(1);
            return Ok(res);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            return Problem("Problem retrieving Data");
        }
    }
    
    [HttpGet("admin")]
    [Authorize(Roles = UserRoles.Admin)]
    public ActionResult GetV2([FromHeader(Name = "x-user-id")] long userId)
    {
        try
        {
            return Ok($"UserId is {userId}");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            return Problem("Problem retrieving Data");
        }
    }
}